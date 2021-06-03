package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IfStatement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("IfStatement", 0, 0, Rhs(List(Terminal("if"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false), Terminal("else"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-if-statement-static-semantics-early-errors",
    "sec-if-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsLabelledFunction Statement)
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if IsLabelledFunction(|Statement|) is *true*.""",
    """        </li>""",
  )
}
