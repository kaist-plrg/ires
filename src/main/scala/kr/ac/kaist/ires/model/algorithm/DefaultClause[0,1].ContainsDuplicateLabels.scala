package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DefaultClause[0,1].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("DefaultClause", 0, 1, Rhs(List(Terminal("default"), Terminal(":"), NonTerminal("StatementList", List(""), true)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= StatementList absent)) {
  |    access __x0__ = (StatementList "ContainsDuplicateLabels" labelSet)
  |    return __x0__
  |  } else 1:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the |StatementList| is present, return ContainsDuplicateLabels of |StatementList| with argument _labelSet_.""",
    """        1. Return *false*.""",
  )
}
