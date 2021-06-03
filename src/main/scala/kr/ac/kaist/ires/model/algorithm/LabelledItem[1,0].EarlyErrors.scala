package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledItem[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LabelledItem", 1, 0, Rhs(List(NonTerminal("FunctionDeclaration", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-labelled-statements-static-semantics-early-errors",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""if true throw SyntaxError else 2:{}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if any source text matches this rule.""",
    """        </li>""",
  )
}
