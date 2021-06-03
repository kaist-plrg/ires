package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.raw` extends Algo {
  val head = BuiltinHead(parseRef("""String.raw"""), List(Param("template", Normal), Param("substitutions", Variadic)))
  val ids = List(
    "sec-string.raw",
    "sec-properties-of-the-string-constructor",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let numberOfSubstitutions = substitutions.length
  |  1:app __x0__ = (ToObject template)
  |  1:let cooked = [? __x0__]
  |  2:app __x1__ = (Get cooked "raw")
  |  2:app __x2__ = (ToObject [? __x1__])
  |  2:let raw = [? __x2__]
  |  3:app __x3__ = (LengthOfArrayLike raw)
  |  3:let literalSegments = [? __x3__]
  |  4:if (! (< 0i literalSegments)) return "" else 6:{}
  |  5:let stringElements = (new [])
  |  6:let nextIndex = 0i
  |  7:while true {
  |    8:app __x4__ = (ToString nextIndex)
  |    8:let nextKey = [! __x4__]
  |    9:app __x5__ = (Get raw nextKey)
  |    9:app __x6__ = (ToString [? __x5__])
  |    9:let nextSeg = [? __x6__]
  |    10:let __x7__ = nextSeg
  |    10:let __x8__ = 0i
  |    10:while (< __x8__ __x7__.length) {
  |      let __x9__ = __x7__[__x8__]
  |      append __x9__ -> stringElements
  |      __x8__ = (+ __x8__ 1i)
  |    }
  |    11:if (== (+ nextIndex 1i) literalSegments) ??? "Return the String value whose code units are the elements in the List id:{stringElements} . If id:{stringElements} has no elements , the empty String is returned ." else 6:{}
  |    14:if (< nextIndex numberOfSubstitutions) let next = substitutions[nextIndex] else let next = ""
  |    15:app __x10__ = (ToString next)
  |    15:let nextSub = [? __x10__]
  |    16:let __x11__ = nextSub
  |    16:let __x12__ = 0i
  |    16:while (< __x12__ __x11__.length) {
  |      let __x13__ = __x11__[__x12__]
  |      append __x13__ -> stringElements
  |      __x12__ = (+ __x12__ 1i)
  |    }
  |    17:nextIndex = (+ nextIndex 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _numberOfSubstitutions_ be the number of elements in _substitutions_.""",
    """          1. Let _cooked_ be ? ToObject(_template_).""",
    """          1. Let _raw_ be ? ToObject(? Get(_cooked_, *"raw"*)).""",
    """          1. Let _literalSegments_ be ? LengthOfArrayLike(_raw_).""",
    """          1. If _literalSegments_ ≤ 0, return the empty String.""",
    """          1. Let _stringElements_ be a new empty List.""",
    """          1. Let _nextIndex_ be 0.""",
    """          1. Repeat,""",
    """            1. Let _nextKey_ be ! ToString(𝔽(_nextIndex_)).""",
    """            1. Let _nextSeg_ be ? ToString(? Get(_raw_, _nextKey_)).""",
    """            1. Append the code unit elements of _nextSeg_ to the end of _stringElements_.""",
    """            1. If _nextIndex_ + 1 = _literalSegments_, then""",
    """              1. Return the String value whose code units are the elements in the List _stringElements_. If _stringElements_ has no elements, the empty String is returned.""",
    """            1. If _nextIndex_ < _numberOfSubstitutions_, let _next_ be _substitutions_[_nextIndex_].""",
    """            1. Else, let _next_ be the empty String.""",
    """            1. Let _nextSub_ be ? ToString(_next_).""",
    """            1. Append the code unit elements of _nextSub_ to the end of _stringElements_.""",
    """            1. Set _nextIndex_ to _nextIndex_ + 1.""",
  )
}
