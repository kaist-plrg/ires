package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisBooleanValue` extends Algo {
  val head = NormalHead("thisBooleanValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-boolean-prototype-object",
    "sec-boolean-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof value) Boolean) return value else 73:{}
  |  1:if (&& (= (typeof value) Object) (! (= value.BooleanData absent))) {
  |    2:let b = value.BooleanData
  |    3:assert (= (typeof b) Boolean)
  |    4:return b
  |  } else 73:{}
  |  5:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is Boolean, return _value_.""",
    """        1. If Type(_value_) is Object and _value_ has a [[BooleanData]] internal slot, then""",
    """          1. Let _b_ be _value_.[[BooleanData]].""",
    """          1. Assert: Type(_b_) is Boolean.""",
    """          1. Return _b_.""",
    """        1. Throw a *TypeError* exception.""",
  )
}
