package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DecimalBigIntegerLiteral[1,1].NumericValue` extends Algo {
  val head = SyntaxDirectedHead("DecimalBigIntegerLiteral", 1, 1, Rhs(List(NonTerminal("NonZeroDigit", List(""), false), NonTerminal("DecimalDigits", List(""), false), NonTerminal("BigIntLiteralSuffix", List(""), false)), None), "NumericValue", List())
  val ids = List(
    "sec-numericvalue",
    "sec-literals-numeric-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{n} be the number of code points in nt:{DecimalDigits} , excluding all occurrences of nt:{NumericLiteralSeparator} ."
  |  1:access __nzd__ = (NonZeroDigit "MV")
  |  1:access __dds__ = (DecimalDigits "MV")
  |  1:let mv = (+ (* __nzd__ 10.0) __dds__)
  |  2:return (convert mv num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be the number of code points in |DecimalDigits|, excluding all occurrences of |NumericLiteralSeparator|.""",
    """          1. Let _mv_ be (the MV of |NonZeroDigit| × 10) plus the MV of |DecimalDigits|.""",
    """          1. Return ℤ(_mv_).""",
  )
}
