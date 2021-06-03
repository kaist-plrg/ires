package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClause[0,1].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("CaseClause", 0, 1, Rhs(List(Terminal("case"), NonTerminal("Expression", List(""), false), Terminal(":"), NonTerminal("StatementList", List(""), true)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= StatementList absent)) {
  |    access __x0__ = (StatementList "ContainsUndefinedContinueTarget" iterationSet (new []))
  |    return __x0__
  |  } else 1:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the |StatementList| is present, return ContainsUndefinedContinueTarget of |StatementList| with arguments _iterationSet_ and « ».""",
    """        1. Return *false*.""",
  )
}
