package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Number.isInteger` extends Algo {
  val head = BuiltinHead(parseRef("""Number.isInteger"""), List(Param("number", Normal)))
  val ids = List(
    "sec-number.isinteger",
    "sec-properties-of-the-number-constructor",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsIntegralNumber number)
  |  0:return [! __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ! IsIntegralNumber(_number_).""",
  )
}
