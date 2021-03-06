package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NumericLiteral[0,0].NumericValue` extends Algo {
  val head = SyntaxDirectedHead("NumericLiteral", 0, 0, Rhs(List(NonTerminal("DecimalLiteral", List(""), false)), None), "NumericValue", List())
  val ids = List(
    "sec-numericvalue",
    "sec-literals-numeric-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "Return the Number value that results from rounding the MV of nt:{DecimalLiteral} as described below ."""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the Number value that results from rounding the MV of |DecimalLiteral| as described below.""",
  )
}
