package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.reverse` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.reverse"""), List())
  val ids = List(
    "sec-array.prototype.reverse",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (floor (/ len 2i))
  |  2:let middle = __x2__
  |  3:let lower = 0i
  |  4:while (! (== lower middle)) {
  |    5:let upper = (- (- len lower) 1i)
  |    6:app __x3__ = (ToString upper)
  |    6:let upperP = [! __x3__]
  |    7:app __x4__ = (ToString lower)
  |    7:let lowerP = [! __x4__]
  |    8:app __x5__ = (HasProperty O lowerP)
  |    8:let lowerExists = [? __x5__]
  |    9:if (= lowerExists true) {
  |      10:app __x6__ = (Get O lowerP)
  |      10:let lowerValue = [? __x6__]
  |    } else 25:{}
  |    11:app __x7__ = (HasProperty O upperP)
  |    11:let upperExists = [? __x7__]
  |    12:if (= upperExists true) {
  |      13:app __x8__ = (Get O upperP)
  |      13:let upperValue = [? __x8__]
  |    } else 25:{}
  |    23:if (&& (= lowerExists true) (= upperExists true)) {
  |      15:app __x9__ = (Set O lowerP upperValue true)
  |      15:[? __x9__]
  |      16:app __x10__ = (Set O upperP lowerValue true)
  |      16:[? __x10__]
  |    } else if (&& (= lowerExists false) (= upperExists true)) {
  |      18:app __x11__ = (Set O lowerP upperValue true)
  |      18:[? __x11__]
  |      19:app __x12__ = (DeletePropertyOrThrow O upperP)
  |      19:[? __x12__]
  |    } else if (&& (= lowerExists true) (= upperExists false)) {
  |      21:app __x13__ = (DeletePropertyOrThrow O lowerP)
  |      21:[? __x13__]
  |      22:app __x14__ = (Set O upperP lowerValue true)
  |      22:[? __x14__]
  |    } else assert (&& (= lowerExists false) (= upperExists false))
  |    26:lower = (+ lower 1i)
  |  }
  |  27:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _middle_ be floor(_len_ / 2).""",
    """          1. Let _lower_ be 0.""",
    """          1. Repeat, while _lower_ ≠ _middle_,""",
    """            1. Let _upper_ be _len_ - _lower_ - 1.""",
    """            1. Let _upperP_ be ! ToString(𝔽(_upper_)).""",
    """            1. Let _lowerP_ be ! ToString(𝔽(_lower_)).""",
    """            1. Let _lowerExists_ be ? HasProperty(_O_, _lowerP_).""",
    """            1. If _lowerExists_ is *true*, then""",
    """              1. Let _lowerValue_ be ? Get(_O_, _lowerP_).""",
    """            1. Let _upperExists_ be ? HasProperty(_O_, _upperP_).""",
    """            1. If _upperExists_ is *true*, then""",
    """              1. Let _upperValue_ be ? Get(_O_, _upperP_).""",
    """            1. If _lowerExists_ is *true* and _upperExists_ is *true*, then""",
    """              1. Perform ? Set(_O_, _lowerP_, _upperValue_, *true*).""",
    """              1. Perform ? Set(_O_, _upperP_, _lowerValue_, *true*).""",
    """            1. Else if _lowerExists_ is *false* and _upperExists_ is *true*, then""",
    """              1. Perform ? Set(_O_, _lowerP_, _upperValue_, *true*).""",
    """              1. Perform ? DeletePropertyOrThrow(_O_, _upperP_).""",
    """            1. Else if _lowerExists_ is *true* and _upperExists_ is *false*, then""",
    """              1. Perform ? DeletePropertyOrThrow(_O_, _lowerP_).""",
    """              1. Perform ? Set(_O_, _upperP_, _lowerValue_, *true*).""",
    """            1. Else,""",
    """              1. Assert: _lowerExists_ and _upperExists_ are both *false*.""",
    """              1. No action is required.""",
    """            1. Set _lower_ to _lower_ + 1.""",
    """          1. Return _O_.""",
  )
}
