package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DecimalBigIntegerLiteral[0,0].NumericValue` extends Algo {
  val head = SyntaxDirectedHead("DecimalBigIntegerLiteral", 0, 0, Rhs(List(Terminal("0"), NonTerminal("BigIntLiteralSuffix", List(""), false)), None), "NumericValue", List())
  val ids = List(
    "sec-numericvalue",
    "sec-literals-numeric-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""return 0i""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *0*<sub>ℤ</sub>.""",
  )
}
