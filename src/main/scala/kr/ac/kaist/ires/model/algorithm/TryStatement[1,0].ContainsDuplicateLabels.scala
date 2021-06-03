package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[1,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 1, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Finally", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "ContainsDuplicateLabels" labelSet)
  |  0:let hasDuplicates = __x0__
  |  1:if (= hasDuplicates true) return true else 1:{}
  |  2:access __x1__ = (Finally "ContainsDuplicateLabels" labelSet)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |Block| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Return ContainsDuplicateLabels of |Finally| with argument _labelSet_.""",
  )
}
