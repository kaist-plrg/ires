package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RegularExpressionFlags[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("RegularExpressionFlags", 1, 0, Rhs(List(NonTerminal("RegularExpressionFlags", List(""), false), NonTerminal("IdentifierPart", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-literals-regular-expression-literals-static-semantics-early-errors",
    "sec-literals-regular-expression-literals",
    "sec-ecmascript-language-lexical-grammar-literals",
    "sec-ecmascript-language-lexical-grammar",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if nt:{IdentifierPart} contains a Unicode escape sequence ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if |IdentifierPart| contains a Unicode escape sequence.""",
    """          </li>""",
  )
}
