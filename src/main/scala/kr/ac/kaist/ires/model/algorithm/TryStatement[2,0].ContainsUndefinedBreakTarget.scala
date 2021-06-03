package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[2,0].ContainsUndefinedBreakTarget` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 2, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Catch", List(""), false), NonTerminal("Finally", List(""), false)), None), "ContainsUndefinedBreakTarget", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedbreaktarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "ContainsUndefinedBreakTarget" labelSet)
  |  0:let hasUndefinedLabels = __x0__
  |  1:if (= hasUndefinedLabels true) return true else 1:{}
  |  2:access __x1__ = (Catch "ContainsUndefinedBreakTarget" labelSet)
  |  2:let hasUndefinedLabels = __x1__
  |  3:if (= hasUndefinedLabels true) return true else 1:{}
  |  4:access __x2__ = (Finally "ContainsUndefinedBreakTarget" labelSet)
  |  4:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasUndefinedLabels_ be ContainsUndefinedBreakTarget of |Block| with argument _labelSet_.""",
    """        1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. Let _hasUndefinedLabels_ be ContainsUndefinedBreakTarget of |Catch| with argument _labelSet_.""",
    """        1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. Return ContainsUndefinedBreakTarget of |Finally| with argument _labelSet_.""",
  )
}
