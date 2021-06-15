package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::thisBigIntValue` extends Algo {
  val head = NormalHead("thisBigIntValue", List(Param("value", Normal)))
  val ids = List(
    "sec-properties-of-the-bigint-prototype-object",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof value) BigInt) return value else 2:{}
  |  1:if (&& (= (typeof value) Object) (! (= value.BigIntData absent))) {
  |    2:assert (= (typeof value.BigIntData) BigInt)
  |    3:return value.BigIntData
  |  } else 2:{}
  |  4:throw TypeError
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_value_) is BigInt, return _value_.""",
    """        1. If Type(_value_) is Object and _value_ has a [[BigIntData]] internal slot, then""",
    """          1. Assert: Type(_value_.[[BigIntData]]) is BigInt.""",
    """          1. Return _value_.[[BigIntData]].""",
    """        1. Throw a *TypeError* exception.""",
  )
}
