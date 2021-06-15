package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ComputedPropertyName[0,0].PropName` extends Algo {
  val head = SyntaxDirectedHead("ComputedPropertyName", 0, 0, Rhs(List(Terminal("["), NonTerminal("AssignmentExpression", List(""), false), Terminal("]")), None), "PropName", List())
  val ids = List(
    "sec-static-semantics-propname",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ~empty~.""",
  )
}
