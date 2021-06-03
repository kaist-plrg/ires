package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DecimalBigIntegerLiteral[1,0].NumericValue` extends Algo {
  val head = SyntaxDirectedHead("DecimalBigIntegerLiteral", 1, 0, Rhs(List(NonTerminal("NonZeroDigit", List(""), false), NonTerminal("BigIntLiteralSuffix", List(""), false)), None), "NumericValue", List())
  val ids = List(
    "sec-numericvalue",
    "sec-literals-numeric-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (NonZeroDigit "MV")
  |  0:return (convert __x0__ num2bigint )
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the BigInt value that represents the MV of |NonZeroDigit|.""",
  )
}
