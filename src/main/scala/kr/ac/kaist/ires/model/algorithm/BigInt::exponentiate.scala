package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::exponentiate` extends Algo {
  val head = NormalHead("BigInt::exponentiate", List(Param("base", Normal), Param("exponent", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-exponentiate",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (< exponent 0i) throw RangeError else 9:{}
  |  1:if (&& (= base 0i) (= exponent 0i)) return 1i else 9:{}
  |  2:return (convert (** base exponent) num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _exponent_ < *0*<sub>ℤ</sub>, throw a *RangeError* exception.""",
    """            1. If _base_ is *0*<sub>ℤ</sub> and _exponent_ is *0*<sub>ℤ</sub>, return *1*<sub>ℤ</sub>.""",
    """            1. Return the BigInt value that represents ℝ(_base_) raised to the power ℝ(_exponent_).""",
  )
}
