package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Statement[2,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("Statement", 2, 0, Rhs(List(NonTerminal("EmptyStatement", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
