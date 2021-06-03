package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BinaryOr` extends Algo {
  val head = NormalHead("BinaryOr", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-binaryor",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (= x 0i) (= x 1i))
  |  1:assert (|| (= y 0i) (= y 1i))
  |  3:if (|| (= x 1i) (= y 1i)) return 1i else return 0i
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _x_ is 0 or 1.""",
    """            1. Assert: _y_ is 0 or 1.""",
    """            1. If _x_ is 1 or _y_ is 1, return 1.""",
    """            1. Else, return 0.""",
  )
}
