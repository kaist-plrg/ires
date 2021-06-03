package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ECMAScriptFunctionObject.Construct` extends Algo {
  val head = MethodHead("ECMAScriptFunctionObject", "Construct", Param("F", Normal), List(Param("argumentsList", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-ecmascript-function-objects-construct-argumentslist-newtarget",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:assert (= (typeof newTarget) Object)
  |  2:let callerContext = CONTEXT
  |  3:let kind = F.ConstructorKind
  |  4:if (= kind CONST_base) {
  |    5:app __x0__ = (OrdinaryCreateFromConstructor newTarget "%Object.prototype%")
  |    5:let thisArgument = [? __x0__]
  |  } else 7:{}
  |  6:app __x1__ = (PrepareForOrdinaryCall F newTarget)
  |  6:let calleeContext = __x1__
  |  8:if (= kind CONST_base) {
  |    app __x2__ = (OrdinaryCallBindThis F calleeContext thisArgument)
  |    __x2__
  |  } else 7:{}
  |  9:access __x3__ = (calleeContext "LexicalEnvironment")
  |  9:let constructorEnv = __x3__
  |  10:app __x4__ = (OrdinaryCallEvaluateBody F argumentsList)
  |  10:let result = __x4__
  |  11:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] calleeContext) {
  |    let __x5__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x5__)
  |  } else {}
  |  11:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  16:if (= result.Type CONST_return) {
  |    13:if (= (typeof result.Value) Object) return result.Value else 7:{}
  |    14:if (= kind CONST_base) return thisArgument else 7:{}
  |    15:if (! (= result.Value undefined)) throw TypeError else 7:{}
  |  } else [? result]
  |  17:app __x6__ = (constructorEnv.GetThisBinding constructorEnv)
  |  17:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _F_ is an ECMAScript function object.""",
    """        1. Assert: Type(_newTarget_) is Object.""",
    """        1. Let _callerContext_ be the running execution context.""",
    """        1. Let _kind_ be _F_.[[ConstructorKind]].""",
    """        1. If _kind_ is ~base~, then""",
    """          1. Let _thisArgument_ be ? OrdinaryCreateFromConstructor(_newTarget_, *"%Object.prototype%"*).""",
    """        1. Let _calleeContext_ be PrepareForOrdinaryCall(_F_, _newTarget_).""",
    """        1. Assert: _calleeContext_ is now the running execution context.""",
    """        1. If _kind_ is ~base~, perform OrdinaryCallBindThis(_F_, _calleeContext_, _thisArgument_).""",
    """        1. Let _constructorEnv_ be the LexicalEnvironment of _calleeContext_.""",
    """        1. Let _result_ be OrdinaryCallEvaluateBody(_F_, _argumentsList_).""",
    """        1. Remove _calleeContext_ from the execution context stack and restore _callerContext_ as the running execution context.""",
    """        1. If _result_.[[Type]] is ~return~, then""",
    """          1. If Type(_result_.[[Value]]) is Object, return NormalCompletion(_result_.[[Value]]).""",
    """          1. If _kind_ is ~base~, return NormalCompletion(_thisArgument_).""",
    """          1. If _result_.[[Value]] is not *undefined*, throw a *TypeError* exception.""",
    """        1. Else, ReturnIfAbrupt(_result_).""",
    """        1. Return ? _constructorEnv_.GetThisBinding().""",
  )
}
