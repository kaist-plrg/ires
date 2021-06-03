package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrepareForOrdinaryCall` extends Algo {
  val head = NormalHead("PrepareForOrdinaryCall", List(Param("F", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-prepareforordinarycall",
    "sec-ecmascript-function-objects-call-thisargument-argumentslist",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (= (typeof newTarget) Undefined) (= (typeof newTarget) Object))
  |  1:let callerContext = CONTEXT
  |  2:let calleeContext = (new ExecutionContext("SubMap" -> (new SubMap())))
  |  3:calleeContext.Function = F
  |  4:let calleeRealm = F.Realm
  |  5:calleeContext.Realm = calleeRealm
  |  6:calleeContext.ScriptOrModule = F.ScriptOrModule
  |  7:app __x0__ = (NewFunctionEnvironment F newTarget)
  |  7:let localEnv = __x0__
  |  8:calleeContext.LexicalEnvironment = localEnv
  |  9:calleeContext.VariableEnvironment = localEnv
  |  10:if (= callerContext null) CONTEXT = null else 12:{}
  |  11:append calleeContext -> EXECUTION_STACK
  |  11:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  13:return calleeContext
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_newTarget_) is Undefined or Object.""",
    """          1. Let _callerContext_ be the running execution context.""",
    """          1. Let _calleeContext_ be a new ECMAScript code execution context.""",
    """          1. Set the Function of _calleeContext_ to _F_.""",
    """          1. Let _calleeRealm_ be _F_.[[Realm]].""",
    """          1. Set the Realm of _calleeContext_ to _calleeRealm_.""",
    """          1. Set the ScriptOrModule of _calleeContext_ to _F_.[[ScriptOrModule]].""",
    """          1. Let _localEnv_ be NewFunctionEnvironment(_F_, _newTarget_).""",
    """          1. Set the LexicalEnvironment of _calleeContext_ to _localEnv_.""",
    """          1. Set the VariableEnvironment of _calleeContext_ to _localEnv_.""",
    """          1. If _callerContext_ is not already suspended, suspend _callerContext_.""",
    """          1. Push _calleeContext_ onto the execution context stack; _calleeContext_ is now the running execution context.""",
    """          1. NOTE: Any exception objects produced after this point are associated with _calleeRealm_.""",
    """          1. Return _calleeContext_.""",
  )
}
