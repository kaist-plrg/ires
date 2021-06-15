package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::toString` extends Algo {
  val head = NormalHead("BigInt::toString", List(Param("x", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-tostring",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (< x 0i) {
  |    app __x0__ = (PRIMITIVE[BigInt].toString (- x))
  |    return (+ "-" [! __x0__])
  |  } else 23:{}
  |  1:return (convert x num2str )
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ < *0*<sub>ℤ</sub>, return the string-concatenation of the String *"-"* and ! BigInt::toString(-_x_).""",
    """            1. Return the String value consisting of the code units of the digits of the decimal representation of _x_.""",
  )
}
