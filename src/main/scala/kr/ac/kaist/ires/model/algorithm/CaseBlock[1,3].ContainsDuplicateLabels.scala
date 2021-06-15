package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= CaseClauses0 absent)) {
  |    1:access __x0__ = (CaseClauses0 "ContainsDuplicateLabels" labelSet)
  |    1:let hasDuplicates = __x0__
  |    2:if (= hasDuplicates true) return true else 1:{}
  |  } else 1:{}
  |  3:access __x1__ = (DefaultClause "ContainsDuplicateLabels" labelSet)
  |  3:let hasDuplicates = __x1__
  |  4:if (= hasDuplicates true) return true else 1:{}
  |  5:if (= CaseClauses1 absent) return false else 1:{}
  |  6:access __x2__ = (CaseClauses1 "ContainsDuplicateLabels" labelSet)
  |  6:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, then""",
    """          1. Let _hasDuplicates_ be ContainsDuplicateLabels of the first |CaseClauses| with argument _labelSet_.""",
    """          1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |DefaultClause| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. If the second |CaseClauses| is not present, return *false*.""",
    """        1. Return ContainsDuplicateLabels of the second |CaseClauses| with argument _labelSet_.""",
  )
}
