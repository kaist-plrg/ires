package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::unaryMinus` extends Algo {
  val head = NormalHead("BigInt::unaryMinus", List(Param("x", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-unaryMinus",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= x 0i) return 0i else 9:{}
  |  1:return (convert (- x) num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ is *0*<sub>ℤ</sub>, return *0*<sub>ℤ</sub>.""",
    """            1. Return the BigInt value that represents the negation of ℝ(_x_).""",
  )
}
