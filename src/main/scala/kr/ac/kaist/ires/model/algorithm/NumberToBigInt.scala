package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NumberToBigInt` extends Algo {
  val head = NormalHead("NumberToBigInt", List(Param("number", Normal)))
  val ids = List(
    "sec-numbertobigint",
    "sec-bigint-constructor-number-value",
    "sec-bigint-constructor",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsIntegralNumber number)
  |  0:if (= __x0__ false) throw RangeError else 2:{}
  |  1:return (convert number num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If IsIntegralNumber(_number_) is *false*, throw a *RangeError* exception.""",
    """            1. Return the BigInt value that represents ℝ(_number_).""",
  )
}
