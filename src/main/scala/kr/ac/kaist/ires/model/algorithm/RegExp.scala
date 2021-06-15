package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExp` extends Algo {
  val head = BuiltinHead(parseRef("""RegExp"""), List(Param("pattern", Normal), Param("flags", Normal)))
  val ids = List(
    "sec-regexp-pattern-flags",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsRegExp pattern)
  |  0:let patternIsRegExp = [? __x0__]
  |  6:if (= NewTarget undefined) {
  |    2:let newTarget = CONTEXT.Function
  |    3:if (&& (= patternIsRegExp true) (= flags undefined)) {
  |      4:app __x1__ = (Get pattern "constructor")
  |      4:let patternConstructor = [? __x1__]
  |      5:app __x2__ = (SameValue newTarget patternConstructor)
  |      5:if (= __x2__ true) return pattern else 0:{}
  |    } else 0:{}
  |  } else let newTarget = NewTarget
  |  16:if (&& (= (typeof pattern) Object) (! (= pattern.RegExpMatcher absent))) {
  |    8:let P = pattern.OriginalSource
  |    10:if (= flags undefined) let F = pattern.OriginalFlags else let F = flags
  |  } else if (= patternIsRegExp true) {
  |    12:app __x3__ = (Get pattern "source")
  |    12:let P = [? __x3__]
  |    15:if (= flags undefined) {
  |      14:app __x4__ = (Get pattern "flags")
  |      14:let F = [? __x4__]
  |    } else let F = flags
  |  } else {
  |    17:let P = pattern
  |    18:let F = flags
  |  }
  |  19:app __x5__ = (RegExpAlloc newTarget)
  |  19:let O = [? __x5__]
  |  20:app __x6__ = (RegExpInitialize O P F)
  |  20:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _patternIsRegExp_ be ? IsRegExp(_pattern_).""",
    """          1. If NewTarget is *undefined*, then""",
    """            1. Let _newTarget_ be the active function object.""",
    """            1. If _patternIsRegExp_ is *true* and _flags_ is *undefined*, then""",
    """              1. Let _patternConstructor_ be ? Get(_pattern_, *"constructor"*).""",
    """              1. If SameValue(_newTarget_, _patternConstructor_) is *true*, return _pattern_.""",
    """          1. Else, let _newTarget_ be NewTarget.""",
    """          1. If Type(_pattern_) is Object and _pattern_ has a [[RegExpMatcher]] internal slot, then""",
    """            1. Let _P_ be _pattern_.[[OriginalSource]].""",
    """            1. If _flags_ is *undefined*, let _F_ be _pattern_.[[OriginalFlags]].""",
    """            1. Else, let _F_ be _flags_.""",
    """          1. Else if _patternIsRegExp_ is *true*, then""",
    """            1. Let _P_ be ? Get(_pattern_, *"source"*).""",
    """            1. If _flags_ is *undefined*, then""",
    """              1. Let _F_ be ? Get(_pattern_, *"flags"*).""",
    """            1. Else, let _F_ be _flags_.""",
    """          1. Else,""",
    """            1. Let _P_ be _pattern_.""",
    """            1. Let _F_ be _flags_.""",
    """          1. Let _O_ be ? RegExpAlloc(_newTarget_).""",
    """          1. Return ? RegExpInitialize(_O_, _P_, _F_).""",
  )
}
