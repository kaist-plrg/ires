package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegExpCreate` extends Algo {
  val head = NormalHead("RegExpCreate", List(Param("P", Normal), Param("F", Normal)))
  val ids = List(
    "sec-regexpcreate",
    "sec-abstract-operations-for-the-regexp-constructor",
    "sec-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RegExpAlloc INTRINSIC_RegExp)
  |  0:let obj = [? __x0__]
  |  1:app __x1__ = (RegExpInitialize obj P F)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _obj_ be ? RegExpAlloc(%RegExp%).""",
    """            1. Return ? RegExpInitialize(_obj_, _P_, _F_).""",
  )
}
