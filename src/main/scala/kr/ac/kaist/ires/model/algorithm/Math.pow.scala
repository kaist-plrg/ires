package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Math.pow` extends Algo {
  val head = BuiltinHead(parseRef("""Math.pow"""), List(Param("base", Normal), Param("exponent", Normal)))
  val ids = List(
    "sec-math.pow",
    "sec-function-properties-of-the-math-object",
    "sec-math-object",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber base)
  |  0:base = [? __x0__]
  |  1:app __x1__ = (ToNumber exponent)
  |  1:exponent = [? __x1__]
  |  2:app __x2__ = (PRIMITIVE[Number].exponentiate base exponent)
  |  2:return [! __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Set _base_ to ? ToNumber(_base_).""",
    """          1. Set _exponent_ to ? ToNumber(_exponent_).""",
    """          1. Return ! Number::exponentiate(_base_, _exponent_).""",
  )
}
