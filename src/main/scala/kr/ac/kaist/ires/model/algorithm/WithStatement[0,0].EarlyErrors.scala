package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WithStatement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("WithStatement", 0, 0, Rhs(List(Terminal("with"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-with-statement-static-semantics-early-errors",
    "sec-with-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if true throw SyntaxError else 2:{}
  |  1:app __x0__ = (IsLabelledFunction Statement)
  |  1:if (= __x0__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if the code that matches this production is contained in strict mode code.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if IsLabelledFunction(|Statement|) is *true*.""",
    """        </li>""",
  )
}
