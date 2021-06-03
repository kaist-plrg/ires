package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.toString` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.toString"""), List())
  val ids = List(
    "sec-date.prototype.tostring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let tv = [? __x0__]
  |  1:app __x1__ = (ToDateString tv)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _tv_ be ? thisTimeValue(*this* value).""",
    """          1. Return ToDateString(_tv_).""",
  )
}
