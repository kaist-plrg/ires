package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.slice` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.slice"""), List(Param("start", Normal), Param("end", Normal)))
  val ids = List(
    "sec-string.prototype.slice",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:let len = S.length
  |  3:app __x2__ = (ToIntegerOrInfinity start)
  |  3:let intStart = [? __x2__]
  |  6:if (= intStart -Infinity) let from = 0i else if (< intStart 0i) {
  |    app __x3__ = (max (+ len intStart) 0i)
  |    let from = __x3__
  |  } else {
  |    app __x4__ = (min intStart len)
  |    let from = __x4__
  |  }
  |  7:if (= end undefined) let intEnd = len else {
  |    app __x5__ = (ToIntegerOrInfinity end)
  |    let intEnd = [? __x5__]
  |  }
  |  10:if (= intEnd -Infinity) let to = 0i else if (< intEnd 0i) {
  |    app __x6__ = (max (+ len intEnd) 0i)
  |    let to = __x6__
  |  } else {
  |    app __x7__ = (min intEnd len)
  |    let to = __x7__
  |  }
  |  11:if (! (< from to)) return "" else 5:{}
  |  12:let __x8__ = ""
  |  12:let __x9__ = from
  |  12:while (< __x9__ (+ to 1i)) {
  |    access __x10__ = (S __x9__)
  |    __x8__ = (+ __x8__ __x10__)
  |    __x9__ = (+ __x9__ 1i)
  |  }
  |  12:return __x8__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. Let _intStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _intStart_ is -∞, let _from_ be 0.""",
    """          1. Else if _intStart_ < 0, let _from_ be max(_len_ + _intStart_, 0).""",
    """          1. Else, let _from_ be min(_intStart_, _len_).""",
    """          1. If _end_ is *undefined*, let _intEnd_ be _len_; else let _intEnd_ be ? ToIntegerOrInfinity(_end_).""",
    """          1. If _intEnd_ is -∞, let _to_ be 0.""",
    """          1. Else if _intEnd_ < 0, let _to_ be max(_len_ + _intEnd_, 0).""",
    """          1. Else, let _to_ be min(_intEnd_, _len_).""",
    """          1. If _from_ ≥ _to_, return the empty String.""",
    """          1. Return the substring of _S_ from _from_ to _to_.""",
  )
}
