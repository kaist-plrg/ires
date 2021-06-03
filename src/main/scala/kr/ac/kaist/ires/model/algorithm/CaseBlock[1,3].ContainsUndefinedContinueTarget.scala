package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= CaseClauses0 absent)) {
  |    1:access __x0__ = (CaseClauses0 "ContainsUndefinedContinueTarget" iterationSet (new []))
  |    1:let hasUndefinedLabels = __x0__
  |    2:if (= hasUndefinedLabels true) return true else 1:{}
  |  } else 1:{}
  |  3:access __x1__ = (DefaultClause "ContainsUndefinedContinueTarget" iterationSet (new []))
  |  3:let hasUndefinedLabels = __x1__
  |  4:if (= hasUndefinedLabels true) return true else 1:{}
  |  5:if (= CaseClauses1 absent) return false else 1:{}
  |  6:access __x2__ = (CaseClauses1 "ContainsUndefinedContinueTarget" iterationSet (new []))
  |  6:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, then""",
    """          1. Let _hasUndefinedLabels_ be ContainsUndefinedContinueTarget of the first |CaseClauses| with arguments _iterationSet_ and « ».""",
    """          1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. Let _hasUndefinedLabels_ be ContainsUndefinedContinueTarget of |DefaultClause| with arguments _iterationSet_ and « ».""",
    """        1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. If the second |CaseClauses| is not present, return *false*.""",
    """        1. Return ContainsUndefinedContinueTarget of the second |CaseClauses| with arguments _iterationSet_ and « ».""",
  )
}
