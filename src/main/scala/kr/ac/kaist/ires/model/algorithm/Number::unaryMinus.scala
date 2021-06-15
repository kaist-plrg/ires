package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::unaryMinus` extends Algo {
  val head = NormalHead("Number::unaryMinus", List(Param("x", Normal)))
  val ids = List(
    "sec-numeric-types-number-unaryMinus",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:if (= x NaN) return NaN else 2:{}
  |  1:return (- x)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. If _x_ is *NaN*, return *NaN*.""",
    """            1. Return the result of negating _x_; that is, compute a Number with the same magnitude but opposite sign.""",
  )
}
