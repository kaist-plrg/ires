package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.slice` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.slice"""), List(Param("start", Normal), Param("end", Normal)))
  val ids = List(
    "sec-array.prototype.slice",
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
  |  10:app __x8__ = (max (- final k) 0i)
  |  10:let count = __x8__
  |  11:app __x9__ = (ArraySpeciesCreate O count)
  |  11:let A = [? __x9__]
  |  12:let n = 0i
  |  13:while (< k final) {
  |    14:app __x10__ = (ToString k)
  |    14:let Pk = [! __x10__]
  |    15:app __x11__ = (HasProperty O Pk)
  |    15:let kPresent = [? __x11__]
  |    16:if (= kPresent true) {
  |      17:app __x12__ = (Get O Pk)
  |      17:let kValue = [? __x12__]
  |      18:app __x13__ = (ToString n)
  |      18:app __x14__ = (CreateDataPropertyOrThrow A [! __x13__] kValue)
  |      18:[? __x14__]
  |    } else 25:{}
  |    19:k = (+ k 1i)
  |    20:n = (+ n 1i)
  |  }
  |  21:app __x15__ = (Set A "length" n true)
  |  21:[? __x15__]
  |  22:return A
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
    """          1. Let _count_ be max(_final_ - _k_, 0).""",
    """          1. Let _A_ be ? ArraySpeciesCreate(_O_, _count_).""",
    """          1. Let _n_ be 0.""",
    """          1. Repeat, while _k_ < _final_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kPresent_ be ? HasProperty(_O_, _Pk_).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _kValue_ be ? Get(_O_, _Pk_).""",
    """              1. Perform ? CreateDataPropertyOrThrow(_A_, ! ToString(𝔽(_n_)), _kValue_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """            1. Set _n_ to _n_ + 1.""",
    """          1. [id="step-array-proto-slice-set-length"] Perform ? Set(_A_, *"length"*, 𝔽(_n_), *true*).""",
    """          1. Return _A_.""",
  )
}
