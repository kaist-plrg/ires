package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalChain[7,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("OptionalChain", 7, 0, Rhs(List(NonTerminal("OptionalChain", List(""), false), NonTerminal("TemplateLiteral", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-left-hand-side-expressions-static-semantics-early-errors",
    "sec-static-semantics",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""if true throw SyntaxError else 2:{}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if any code matches this production.""",
    """          </li>""",
  )
}
