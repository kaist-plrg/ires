package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IfStatement[0,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("IfStatement", 0, 0, Rhs(List(Terminal("if"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false), Terminal("else"), NonTerminal("Statement", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement0 "ContainsDuplicateLabels" labelSet)
  |  0:let hasDuplicate = __x0__
  |  1:if (= hasDuplicate true) return true else 1:{}
  |  2:access __x1__ = (Statement1 "ContainsDuplicateLabels" labelSet)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _hasDuplicate_ be ContainsDuplicateLabels of the first |Statement| with argument _labelSet_.""",
    """        1. If _hasDuplicate_ is *true*, return *true*.""",
    """        1. Return ContainsDuplicateLabels of the second |Statement| with argument _labelSet_.""",
  )
}
