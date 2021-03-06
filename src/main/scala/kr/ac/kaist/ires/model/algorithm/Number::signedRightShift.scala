package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number::signedRightShift` extends Algo {
  val head = NormalHead("Number::signedRightShift", List(Param("x", Normal), Param("y", Normal)))
  val ids = List(
    "sec-numeric-types-number-signedRightShift",
    "sec-ecmascript-language-types-number-type",
    "sec-numeric-types",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToInt32 x)
  |  0:let lnum = [! __x0__]
  |  1:app __x1__ = (ToUint32 y)
  |  1:let rnum = [! __x1__]
  |  2:let shiftCount = (%% rnum 32i)
  |  3:return (>> lnum shiftCount)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _lnum_ be ! ToInt32(_x_).""",
    """            1. Let _rnum_ be ! ToUint32(_y_).""",
    """            1. Let _shiftCount_ be ℝ(_rnum_) modulo 32.""",
    """            1. Return the result of performing a sign-extending right shift of _lnum_ by _shiftCount_ bits. The most significant bit is propagated. The mathematical value of the result is exactly representable as a 32-bit two's complement bit string.""",
  )
}
