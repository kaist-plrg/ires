package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::bitwiseNOT` extends Algo {
  val head = NormalHead("Number::bitwiseNOT", List(Param("x", Normal)))
  val ids = List(
    "sec-numeric-types-number-bitwiseNOT",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToInt32 x)
  |  0:let oldValue = [! __x0__]
  |  1:return (~ oldValue)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _oldValue_ be ! ToInt32(_x_).""",
    """            1. Return the result of applying bitwise complement to _oldValue_. The mathematical value of the result is exactly representable as a 32-bit two's complement bit string.""",
  )
}
