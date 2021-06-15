package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToDateString` extends Algo {
  val head = NormalHead("ToDateString", List(Param("tv", Normal)))
  val ids = List(
    "sec-todatestring",
    "sec-date.prototype.tostring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof tv) Number)
  |  1:if (= tv NaN) return "Invalid Date" else 13:{}
  |  2:app __x0__ = (LocalTime tv)
  |  2:let t = __x0__
  |  3:app __x1__ = (DateString t)
  |  3:app __x2__ = (TimeString t)
  |  3:app __x3__ = (TimeZoneString tv)
  |  3:return (+ (+ (+ __x1__ " ") __x2__) __x3__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_tv_) is Number.""",
    """            1. If _tv_ is *NaN*, return *"Invalid Date"*.""",
    """            1. Let _t_ be LocalTime(_tv_).""",
    """            1. Return the string-concatenation of DateString(_t_), the code unit 0x0020 (SPACE), TimeString(_t_), and TimeZoneString(_tv_).""",
  )
}
