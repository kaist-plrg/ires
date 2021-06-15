package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.copyWithin` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.copyWithin"""), List(Param("target", Normal), Param("start", Normal), Param("end", Optional)))
  val ids = List(
    "sec-array.prototype.copywithin",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (ToIntegerOrInfinity target)
  |  2:let relativeTarget = [? __x2__]
  |  5:if (= relativeTarget -Infinity) let to = 0i else if (< relativeTarget 0i) {
  |    app __x3__ = (max (+ len relativeTarget) 0i)
  |    let to = __x3__
  |  } else {
  |    app __x4__ = (min relativeTarget len)
  |    let to = __x4__
  |  }
  |  6:app __x5__ = (ToIntegerOrInfinity start)
  |  6:let relativeStart = [? __x5__]
  |  9:if (= relativeStart -Infinity) let from = 0i else if (< relativeStart 0i) {
  |    app __x6__ = (max (+ len relativeStart) 0i)
  |    let from = __x6__
  |  } else {
  |    app __x7__ = (min relativeStart len)
  |    let from = __x7__
  |  }
  |  10:if (= end undefined) let relativeEnd = len else {
  |    app __x8__ = (ToIntegerOrInfinity end)
  |    let relativeEnd = [? __x8__]
  |  }
  |  13:if (= relativeEnd -Infinity) let final = 0i else if (< relativeEnd 0i) {
  |    app __x9__ = (max (+ len relativeEnd) 0i)
  |    let final = __x9__
  |  } else {
  |    app __x10__ = (min relativeEnd len)
  |    let final = __x10__
  |  }
  |  14:app __x11__ = (min (- final from) (- len to))
  |  14:let count = __x11__
  |  19:if (&& (< from to) (< to (+ from count))) {
  |    16:let direction = -1i
  |    17:from = (- (+ from count) 1i)
  |    18:to = (- (+ to count) 1i)
  |  } else let direction = 1i
  |  21:while (< 0i count) {
  |    22:app __x12__ = (ToString from)
  |    22:let fromKey = [! __x12__]
  |    23:app __x13__ = (ToString to)
  |    23:let toKey = [! __x13__]
  |    24:app __x14__ = (HasProperty O fromKey)
  |    24:let fromPresent = [? __x14__]
  |    28:if (= fromPresent true) {
  |      26:app __x15__ = (Get O fromKey)
  |      26:let fromVal = [? __x15__]
  |      27:app __x16__ = (Set O toKey fromVal true)
  |      27:[? __x16__]
  |    } else {
  |      29:assert (= fromPresent false)
  |      30:app __x17__ = (DeletePropertyOrThrow O toKey)
  |      30:[? __x17__]
  |    }
  |    31:from = (+ from direction)
  |    32:to = (+ to direction)
  |    33:count = (- count 1i)
  |  }
  |  34:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _relativeTarget_ be ? ToIntegerOrInfinity(_target_).""",
    """          1. If _relativeTarget_ is -∞, let _to_ be 0.""",
    """          1. Else if _relativeTarget_ < 0, let _to_ be max(_len_ + _relativeTarget_, 0).""",
    """          1. Else, let _to_ be min(_relativeTarget_, _len_).""",
    """          1. Let _relativeStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _relativeStart_ is -∞, let _from_ be 0.""",
    """          1. Else if _relativeStart_ < 0, let _from_ be max(_len_ + _relativeStart_, 0).""",
    """          1. Else, let _from_ be min(_relativeStart_, _len_).""",
    """          1. If _end_ is *undefined*, let _relativeEnd_ be _len_; else let _relativeEnd_ be ? ToIntegerOrInfinity(_end_).""",
    """          1. If _relativeEnd_ is -∞, let _final_ be 0.""",
    """          1. Else if _relativeEnd_ < 0, let _final_ be max(_len_ + _relativeEnd_, 0).""",
    """          1. Else, let _final_ be min(_relativeEnd_, _len_).""",
    """          1. Let _count_ be min(_final_ - _from_, _len_ - _to_).""",
    """          1. If _from_ < _to_ and _to_ < _from_ + _count_, then""",
    """            1. Let _direction_ be -1.""",
    """            1. Set _from_ to _from_ + _count_ - 1.""",
    """            1. Set _to_ to _to_ + _count_ - 1.""",
    """          1. Else,""",
    """            1. Let _direction_ be 1.""",
    """          1. Repeat, while _count_ > 0,""",
    """            1. Let _fromKey_ be ! ToString(𝔽(_from_)).""",
    """            1. Let _toKey_ be ! ToString(𝔽(_to_)).""",
    """            1. Let _fromPresent_ be ? HasProperty(_O_, _fromKey_).""",
    """            1. If _fromPresent_ is *true*, then""",
    """              1. Let _fromVal_ be ? Get(_O_, _fromKey_).""",
    """              1. Perform ? Set(_O_, _toKey_, _fromVal_, *true*).""",
    """            1. Else,""",
    """              1. Assert: _fromPresent_ is *false*.""",
    """              1. Perform ? DeletePropertyOrThrow(_O_, _toKey_).""",
    """            1. Set _from_ to _from_ + _direction_.""",
    """            1. Set _to_ to _to_ + _direction_.""",
    """            1. Set _count_ to _count_ - 1.""",
    """          1. Return _O_.""",
  )
}
