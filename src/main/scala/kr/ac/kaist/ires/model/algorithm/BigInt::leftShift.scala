package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::leftShift` extends Algo {
  val head = NormalHead("BigInt::leftShift", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-leftShift",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (< y 0i) ??? "Return the BigInt value that represents ℝ ( id:{x} ) / 2 sup:{- id:{y}} , rounding down to the nearest integer , including for negative numbers ." else 9:{}
  |  2:return (convert (* x (** 2.0 y)) num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _y_ < *0*<sub>ℤ</sub>, then""",
    """              1. Return the BigInt value that represents ℝ(_x_) / 2<sup>-_y_</sup>, rounding down to the nearest integer, including for negative numbers.""",
    """            1. Return the BigInt value that represents ℝ(_x_) × 2<sup>_y_</sup>.""",
  )
}
