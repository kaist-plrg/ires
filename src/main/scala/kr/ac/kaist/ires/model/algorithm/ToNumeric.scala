package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToNumeric` extends Algo {
  val head = NormalHead("ToNumeric", List(Param("value", Normal)))
  val ids = List(
    "sec-tonumeric",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToPrimitive value CONST_number)
  |  0:let primValue = [? __x0__]
  |  1:if (= (typeof primValue) BigInt) return primValue else 0:{}
  |  2:app __x1__ = (ToNumber primValue)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _primValue_ be ? ToPrimitive(_value_, ~number~).""",
    """        1. If Type(_primValue_) is BigInt, return _primValue_.""",
    """        1. Return ? ToNumber(_primValue_).""",
  )
}
