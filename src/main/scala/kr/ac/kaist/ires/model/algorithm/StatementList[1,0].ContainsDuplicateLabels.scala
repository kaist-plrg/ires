package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementList[1,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("StatementList", 1, 0, Rhs(List(NonTerminal("StatementList", List(""), false), NonTerminal("StatementListItem", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "ContainsDuplicateLabels" labelSet)
  |  0:let hasDuplicates = __x0__
  |  1:if (= hasDuplicates true) return true else 1:{}
  |  2:access __x1__ = (StatementListItem "ContainsDuplicateLabels" labelSet)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasDuplicates_ be ContainsDuplicateLabels of |StatementList| with argument _labelSet_.""",
    """        1. If _hasDuplicates_ is *true*, return *true*.""",
    """        1. Return ContainsDuplicateLabels of |StatementListItem| with argument _labelSet_.""",
  )
}
