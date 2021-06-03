package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.toTimeString` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.toTimeString"""), List())
  val ids = List(
    "sec-date.prototype.totimestring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (thisTimeValue O)
  |  1:let tv = [? __x0__]
  |  2:if (= tv NaN) return "Invalid Date" else 13:{}
  |  3:app __x1__ = (LocalTime tv)
  |  3:let t = __x1__
  |  4:app __x2__ = (TimeString t)
  |  4:app __x3__ = (TimeZoneString tv)
  |  4:return (+ __x2__ __x3__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be this Date object.""",
    """          1. Let _tv_ be ? thisTimeValue(_O_).""",
    """          1. If _tv_ is *NaN*, return *"Invalid Date"*.""",
    """          1. Let _t_ be LocalTime(_tv_).""",
    """          1. Return the string-concatenation of TimeString(_t_) and TimeZoneString(_tv_).""",
  )
}
