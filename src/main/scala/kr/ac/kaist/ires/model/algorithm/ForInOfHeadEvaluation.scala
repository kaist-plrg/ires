package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfHeadEvaluation` extends Algo {
  val head = NormalHead("ForInOfHeadEvaluation", List(Param("uninitializedBoundNames", Normal), Param("expr", Normal), Param("iterationKind", Normal)))
  val ids = List(
    "sec-runtime-semantics-forinofheadevaluation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let oldEnv = CONTEXT.LexicalEnvironment
  |  1:if (< 0i uninitializedBoundNames.length) {
  |    3:app __x0__ = (NewDeclarativeEnvironment oldEnv)
  |    3:let newEnv = __x0__
  |    4:let __x1__ = uninitializedBoundNames
  |    4:let __x2__ = 0i
  |    4:while (< __x2__ __x1__.length) {
  |      let name = __x1__[__x2__]
  |      5:app __x3__ = (newEnv.CreateMutableBinding newEnv name false)
  |      5:[! __x3__]
  |      __x2__ = (+ __x2__ 1i)
  |    }
  |    6:CONTEXT.LexicalEnvironment = newEnv
  |  } else 2:{}
  |  7:access __x4__ = (expr "Evaluation")
  |  7:let exprRef = __x4__
  |  8:CONTEXT.LexicalEnvironment = oldEnv
  |  9:app __x5__ = (GetValue exprRef)
  |  9:let exprValue = [? __x5__]
  |  17:if (= iterationKind CONST_enumerate) {
  |    11:if (|| (= exprValue undefined) (= exprValue null)) return (new Completion("Type" -> CONST_break, "Value" -> CONST_empty, "Target" -> CONST_empty)) else 2:{}
  |    13:app __x6__ = (ToObject exprValue)
  |    13:let obj = [! __x6__]
  |    14:app __x7__ = (EnumerateObjectProperties obj)
  |    14:let iterator = [? __x7__]
  |    15:app __x8__ = (GetV iterator "next")
  |    15:let nextMethod = [! __x8__]
  |    16:return (new Record("Iterator" -> iterator, "NextMethod" -> nextMethod, "Done" -> false))
  |  } else {
  |    18:assert (|| (= iterationKind CONST_iterate) (= iterationKind CONST_asyncDASHiterate))
  |    20:if (= iterationKind CONST_asyncDASHiterate) let iteratorHint = CONST_async else let iteratorHint = CONST_sync
  |    21:app __x9__ = (GetIterator exprValue iteratorHint)
  |    21:return [? __x9__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _oldEnv_ be the running execution context's LexicalEnvironment.""",
    """          1. If _uninitializedBoundNames_ is not an empty List, then""",
    """            1. Assert: _uninitializedBoundNames_ has no duplicate entries.""",
    """            1. Let _newEnv_ be NewDeclarativeEnvironment(_oldEnv_).""",
    """            1. For each String _name_ of _uninitializedBoundNames_, do""",
    """              1. Perform ! _newEnv_.CreateMutableBinding(_name_, *false*).""",
    """            1. Set the running execution context's LexicalEnvironment to _newEnv_.""",
    """          1. Let _exprRef_ be the result of evaluating _expr_.""",
    """          1. Set the running execution context's LexicalEnvironment to _oldEnv_.""",
    """          1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """          1. If _iterationKind_ is ~enumerate~, then""",
    """            1. If _exprValue_ is *undefined* or *null*, then""",
    """              1. Return Completion { [[Type]]: ~break~, [[Value]]: ~empty~, [[Target]]: ~empty~ }.""",
    """            1. Let _obj_ be ! ToObject(_exprValue_).""",
    """            1. Let _iterator_ be ? EnumerateObjectProperties(_obj_).""",
    """            1. Let _nextMethod_ be ! GetV(_iterator_, *"next"*).""",
    """            1. Return the Record { [[Iterator]]: _iterator_, [[NextMethod]]: _nextMethod_, [[Done]]: *false* }.""",
    """          1. Else,""",
    """            1. Assert: _iterationKind_ is ~iterate~ or ~async-iterate~.""",
    """            1. If _iterationKind_ is ~async-iterate~, let _iteratorHint_ be ~async~.""",
    """            1. Else, let _iteratorHint_ be ~sync~.""",
    """            1. Return ? GetIterator(_exprValue_, _iteratorHint_).""",
  )
}
