package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[2,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 2, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Catch", List(""), false), NonTerminal("Finally", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "ContainsDuplicateLabels" labelSet)
  |  0:let hasDuplicates = __x0__
  |  1:if (= hasDuplicates true) return true else 1:{}
  |  2:access __x1__ = (Catch "ContainsDuplicateLabels" labelSet)
  |  2:let hasDuplicates = __x1__
  |  3:if (= hasDuplicates true) return true else 1:{}
  |  4:access __x2__ = (Finally "ContainsDuplicateLabels" labelSet)
  |  4:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |Block| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |Catch| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Return ContainsDuplicateLabels of |Finally| with argument _labelSet_.""",
  )
}
