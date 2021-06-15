package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[0,0].ContainsUndefinedBreakTarget` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 0, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Catch", List(""), false)), None), "ContainsUndefinedBreakTarget", List(Param("labelSet", Normal)))
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
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasUndefinedLabels_ be ContainsUndefinedBreakTarget of |Block| with argument _labelSet_.""",
    """        1. If _hasUndefinedLabels_ is *true*, return *true*.""",
    """        1. Return ContainsUndefinedBreakTarget of |Catch| with argument _labelSet_.""",
  )
}
