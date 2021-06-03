package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClauses[1,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("CaseClauses", 1, 0, Rhs(List(NonTerminal("CaseClauses", List(""), false), NonTerminal("CaseClause", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CaseClauses "ContainsUndefinedContinueTarget" iterationSet (new []))
  |  0:let hasUndefinedLabels = __x0__
  |  1:if (= hasUndefinedLabels true) return true else 1:{}
  |  2:access __x1__ = (CaseClause "ContainsUndefinedContinueTarget" iterationSet (new []))
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasUndefinedLabels_ be ContainsUndefinedContinueTarget of |CaseClauses| with arguments _iterationSet_ and « ».""",
    """        1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. Return ContainsUndefinedContinueTarget of |CaseClause| with arguments _iterationSet_ and « ».""",
  )
}
