package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::bitwiseOR` extends Algo {
  val head = NormalHead("Number::bitwiseOR", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-bitwiseOR",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (NumberBitwiseOp "|" x y)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Return NumberBitwiseOp(`|`, _x_, _y_).""",
  )
}
