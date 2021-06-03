package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::subtract` extends Algo {
  val head = NormalHead("Number::subtract", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-subtract",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (PRIMITIVE[Number].unaryMinus y)
  |  0:app __x1__ = (PRIMITIVE[Number].add x __x0__)
  |  0:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Return Number::add(_x_, Number::unaryMinus(_y_)).""",
  )
}
