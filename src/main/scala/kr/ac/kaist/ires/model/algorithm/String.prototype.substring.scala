package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.substring` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.substring"""), List(Param("start", Normal), Param("end", Normal)))
  val ids = List(
    "sec-string.prototype.substring",
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
  |  4:if (= end undefined) let intEnd = len else {
  |    app __x3__ = (ToIntegerOrInfinity end)
  |    let intEnd = [? __x3__]
  |  }
  |  5:??? "Let id:{finalStart} be the result of clamping id:{intStart} between 0 and id:{len} ."
  |  6:??? "Let id:{finalEnd} be the result of clamping id:{intEnd} between 0 and id:{len} ."
  |  7:app __x4__ = (min finalStart finalEnd)
  |  7:let from = __x4__
  |  8:app __x5__ = (max finalStart finalEnd)
  |  8:let to = __x5__
  |  9:let __x6__ = ""
  |  9:let __x7__ = from
  |  9:while (< __x7__ (+ to 1i)) {
  |    access __x8__ = (S __x7__)
  |    __x6__ = (+ __x6__ __x8__)
  |    __x7__ = (+ __x7__ 1i)
  |  }
  |  9:return __x6__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. Let _intStart_ be ? ToIntegerOrInfinity(_start_).""",
    """          1. If _end_ is *undefined*, let _intEnd_ be _len_; else let _intEnd_ be ? ToIntegerOrInfinity(_end_).""",
    """          1. Let _finalStart_ be the result of clamping _intStart_ between 0 and _len_.""",
    """          1. Let _finalEnd_ be the result of clamping _intEnd_ between 0 and _len_.""",
    """          1. Let _from_ be min(_finalStart_, _finalEnd_).""",
    """          1. Let _to_ be max(_finalStart_, _finalEnd_).""",
    """          1. Return the substring of _S_ from _from_ to _to_.""",
  )
}
