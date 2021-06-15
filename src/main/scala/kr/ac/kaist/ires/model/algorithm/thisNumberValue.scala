package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisNumberValue` extends Algo {
  val head = NormalHead("thisNumberValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-number-prototype-object",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof value) Number) return value else 3:{}
  |  1:if (&& (= (typeof value) Object) (! (= value.NumberData absent))) {
  |    2:let n = value.NumberData
  |    3:assert (= (typeof n) Number)
  |    4:return n
  |  } else 3:{}
  |  5:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is Number, return _value_.""",
    """        1. If Type(_value_) is Object and _value_ has a [[NumberData]] internal slot, then""",
    """          1. Let _n_ be _value_.[[NumberData]].""",
    """          1. Assert: Type(_n_) is Number.""",
    """          1. Return _n_.""",
    """        1. Throw a *TypeError* exception.""",
  )
}
