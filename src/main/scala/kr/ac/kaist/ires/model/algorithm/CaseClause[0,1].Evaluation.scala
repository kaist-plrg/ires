package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClause[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("CaseClause", 0, 1, Rhs(List(Terminal("case"), NonTerminal("Expression", List(""), false), Terminal(":"), NonTerminal("StatementList", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-switch-statement-runtime-semantics-evaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of evaluating |StatementList|.""",
  )
}
