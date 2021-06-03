package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NumericLiteral[2,0].NumericValue` extends Algo {
  val head = SyntaxDirectedHead("NumericLiteral", 2, 0, Rhs(List(NonTerminal("NonDecimalIntegerLiteral", List(""), false)), None), "NumericValue", List())
  val ids = List(
    "sec-numericvalue",
    "sec-literals-numeric-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "Return the Number value that results from rounding the MV of nt:{NonDecimalIntegerLiteral} as described below ."""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the Number value that results from rounding the MV of |NonDecimalIntegerLiteral| as described below.""",
  )
}
