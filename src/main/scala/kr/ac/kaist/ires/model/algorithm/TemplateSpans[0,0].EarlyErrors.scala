package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 0, 0, Rhs(List(NonTerminal("TemplateTail", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-static-semantics-template-early-errors",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if the sub:{[ Tagged ]} parameter was not set and nt:{TemplateTail} Contains nt:{NotEscapeSequence} ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the <sub>[Tagged]</sub> parameter was not set and |TemplateTail| Contains |NotEscapeSequence|.""",
    """          </li>""",
  )
}
