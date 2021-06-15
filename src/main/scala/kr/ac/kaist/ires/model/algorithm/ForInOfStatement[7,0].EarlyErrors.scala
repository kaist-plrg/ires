package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[7,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 7, 0, Rhs(List(Terminal("for"), Terminal("await"), Terminal("("), Terminal("var"), NonTerminal("ForBinding", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-for-in-and-for-of-statements-static-semantics-early-errors",
    "sec-for-in-and-for-of-statements",
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
