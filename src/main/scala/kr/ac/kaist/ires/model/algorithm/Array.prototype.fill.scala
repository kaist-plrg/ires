package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.fill` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.fill"""), List(Param("value", Normal), Param("start", Optional), Param("end", Optional)))
  val ids = List(
    "sec-array.prototype.fill",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (ToIntegerOrInfinity start)
  |  2:let relativeStart = [? __x2__]
  |  5:if (= relativeStart -Infinity) let k = 0i else if (< relativeStart 0i) {
  |    app __x3__ = (max (+ len relativeStart) 0i)
  |    let k = __x3__
  |  } else {
  |    app __x4__ = (min relativeStart len)
  |    let k = __x4__
  |  }
  |  6:if (= end undefined) let relativeEnd = len else {
  |    app __x5__ = (ToIntegerOrInfinity end)
  |    let relativeEnd = [? __x5__]
  |  }
  |  9:if (= relativeEnd -Infinity) let final = 0i else if (< relativeEnd 0i) {
  |    app __x6__ = (max (+ len relativeEnd) 0i)
  |    let final = __x6__
  |  } else {
  |    app __x7__ = (min relativeEnd len)
  |    let final = __x7__
  |  }
  |  10:while (< k final) {
  |    11:app __x8__ = (ToString k)
  |    11:let Pk = [! __x8__]
  |    12:app __x9__ = (Set O Pk value true)
  |    12:[? __x9__]
  |    13:k = (+ k 1i)
  |  }
  |  14:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _relativeStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _relativeStart_ is -∞, let _k_ be 0.""",
    """          1. Else if _relativeStart_ < 0, let _k_ be max(_len_ + _relativeStart_, 0).""",
    """          1. Else, let _k_ be min(_relativeStart_, _len_).""",
    """          1. If _end_ is *undefined*, let _relativeEnd_ be _len_; else let _relativeEnd_ be ? ToIntegerOrInfinity(_end_).""",
    """          1. If _relativeEnd_ is -∞, let _final_ be 0.""",
    """          1. Else if _relativeEnd_ < 0, let _final_ be max(_len_ + _relativeEnd_, 0).""",
    """          1. Else, let _final_ be min(_relativeEnd_, _len_).""",
    """          1. Repeat, while _k_ < _final_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Perform ? Set(_O_, _Pk_, _value_, *true*).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return _O_.""",
  )
}
