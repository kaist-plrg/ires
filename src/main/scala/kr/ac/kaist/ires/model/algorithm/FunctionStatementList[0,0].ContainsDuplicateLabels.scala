package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionStatementList[0,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("FunctionStatementList", 0, 0, Rhs(List(EmptyToken), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
