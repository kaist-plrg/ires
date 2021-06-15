package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[1,3].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 1, 3, Rhs(List(Terminal("for"), Terminal("("), Terminal("var"), NonTerminal("VariableDeclarationList", List(""), false), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-for-statement-static-semantics-early-errors",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsLabelledFunction Statement)
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if IsLabelledFunction(|Statement|) is *true*.""",
    """          </li>""",
  )
}
