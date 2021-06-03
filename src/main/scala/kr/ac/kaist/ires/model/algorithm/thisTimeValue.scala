package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisTimeValue` extends Algo {
  val head = NormalHead("thisTimeValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (&& (= (typeof value) Object) (! (= value.DateValue absent))) return value.DateValue else 13:{}
  |  2:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is Object and _value_ has a [[DateValue]] internal slot, then""",
    """          1. Return _value_.[[DateValue]].""",
    """        1. Throw a *TypeError* exception.""",
  )
}
