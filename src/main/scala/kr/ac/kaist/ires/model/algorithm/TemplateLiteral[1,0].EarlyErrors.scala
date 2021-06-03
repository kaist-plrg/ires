package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateLiteral[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("TemplateLiteral", 1, 0, Rhs(List(NonTerminal("SubstitutionTemplate", List("?Yield", "?Await", "?Tagged"), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-static-semantics-template-early-errors",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""??? "It is a Syntax Error if the number of elements in the result of TemplateStrings of nt:{TemplateLiteral} with argument value:{false} is greater than 2 sup:{32} - 1 ."""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the number of elements in the result of TemplateStrings of |TemplateLiteral| with argument *false* is greater than 2<sup>32</sup> - 1.""",
    """          </li>""",
  )
}
