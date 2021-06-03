package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BigInt::sameValue` extends Algo {
  val head = NormalHead("BigInt::sameValue", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-bigint-sameValue",
    "sec-ecmascript-language-types-bigint-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (PRIMITIVE[BigInt].equal x y)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Return BigInt::equal(_x_, _y_).""",
  )
}
