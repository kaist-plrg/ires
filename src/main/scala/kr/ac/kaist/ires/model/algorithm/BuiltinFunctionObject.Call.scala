package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BuiltinFunctionObject.Call` extends Algo {
  val head = MethodHead("BuiltinFunctionObject", "Call", Param("F", Normal), List(Param("thisArgument", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-built-in-function-objects-call-thisargument-argumentslist",
    "sec-built-in-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let callerContext = CONTEXT
  |  1:if (= callerContext null) CONTEXT = null else 7:{}
  |  2:let calleeContext = (new ExecutionContext("SubMap" -> (new SubMap())))
  |  3:calleeContext.Function = F
  |  4:let calleeRealm = F.Realm
  |  5:calleeContext.Realm = calleeRealm
  |  6:calleeContext.ScriptOrModule = null
  |  8:append calleeContext -> EXECUTION_STACK
  |  8:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  9:??? "Let id:{result} be the Completion Record that is the result of evaluating id:{F} in a manner that conforms to the specification of id:{F} . id:{thisArgument} is the value:{this} value , id:{argumentsList} provides the named parameters , and the NewTarget value is value:{undefined} ."
  |  10:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] calleeContext) {
  |    let __x0__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x0__)
  |  } else {}
  |  10:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  11:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _callerContext_ be the running execution context.""",
    """        1. If _callerContext_ is not already suspended, suspend _callerContext_.""",
    """        1. Let _calleeContext_ be a new execution context.""",
    """        1. Set the Function of _calleeContext_ to _F_.""",
    """        1. Let _calleeRealm_ be _F_.[[Realm]].""",
    """        1. Set the Realm of _calleeContext_ to _calleeRealm_.""",
    """        1. Set the ScriptOrModule of _calleeContext_ to *null*.""",
    """        1. Perform any necessary implementation-defined initialization of _calleeContext_.""",
    """        1. Push _calleeContext_ onto the execution context stack; _calleeContext_ is now the running execution context.""",
    """        1. [id="step-call-builtin-function-result"] Let _result_ be the Completion Record that is the result of evaluating _F_ in a manner that conforms to the specification of _F_. _thisArgument_ is the *this* value, _argumentsList_ provides the named parameters, and the NewTarget value is *undefined*.""",
    """        1. Remove _calleeContext_ from the execution context stack and restore _callerContext_ as the running execution context.""",
    """        1. Return _result_.""",
  )
}
