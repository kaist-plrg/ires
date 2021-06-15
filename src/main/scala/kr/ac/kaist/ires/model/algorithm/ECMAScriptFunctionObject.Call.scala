package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ECMAScriptFunctionObject.Call` extends Algo {
  val head = MethodHead("ECMAScriptFunctionObject", "Call", Param("F", Normal), List(Param("thisArgument", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-ecmascript-function-objects-call-thisargument-argumentslist",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:let callerContext = CONTEXT
  |  2:app __x0__ = (PrepareForOrdinaryCall F undefined)
  |  2:let calleeContext = __x0__
  |  4:if (= F.IsClassConstructor true) {
  |    5:let error = (new OrdinaryObject())
  |    7:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] calleeContext) {
  |      let __x1__ = (- EXECUTION_STACK.length 1i)
  |      (pop EXECUTION_STACK __x1__)
  |    } else {}
  |    7:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |    8:app __x2__ = (ThrowCompletion error)
  |    8:return __x2__
  |  } else 6:{}
  |  9:app __x3__ = (OrdinaryCallBindThis F calleeContext thisArgument)
  |  9:__x3__
  |  10:app __x4__ = (OrdinaryCallEvaluateBody F argumentsList)
  |  10:let result = __x4__
  |  11:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] calleeContext) {
  |    let __x5__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x5__)
  |  } else {}
  |  11:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  12:if (= result.Type CONST_return) return result.Value else 6:{}
  |  13:[? result]
  |  14:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an ECMAScript function object.""",
    """        1. Let _callerContext_ be the running execution context.""",
    """        1. Let _calleeContext_ be PrepareForOrdinaryCall(_F_, *undefined*).""",
    """        1. Assert: _calleeContext_ is now the running execution context.""",
    """        1. If _F_.[[IsClassConstructor]] is *true*, then""",
    """          1. Let _error_ be a newly created *TypeError* object.""",
    """          1. NOTE: _error_ is created in _calleeContext_ with _F_'s associated Realm Record.""",
    """          1. Remove _calleeContext_ from the execution context stack and restore _callerContext_ as the running execution context.""",
    """          1. Return ThrowCompletion(_error_).""",
    """        1. Perform OrdinaryCallBindThis(_F_, _calleeContext_, _thisArgument_).""",
    """        1. Let _result_ be OrdinaryCallEvaluateBody(_F_, _argumentsList_).""",
    """        1. [id="step-call-pop-context-stack"] Remove _calleeContext_ from the execution context stack and restore _callerContext_ as the running execution context.""",
    """        1. If _result_.[[Type]] is ~return~, return NormalCompletion(_result_.[[Value]]).""",
    """        1. ReturnIfAbrupt(_result_).""",
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
