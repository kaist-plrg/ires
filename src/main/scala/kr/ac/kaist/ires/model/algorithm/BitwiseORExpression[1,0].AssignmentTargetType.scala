package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BitwiseORExpression[1,0].AssignmentTargetType` extends Algo {
  val head = SyntaxDirectedHead("BitwiseORExpression", 1, 0, Rhs(List(NonTerminal("BitwiseORExpression", List(""), false), Terminal("|"), NonTerminal("BitwiseXORExpression", List(""), false)), None), "AssignmentTargetType", List())
  val ids = List(
    "sec-static-semantics-assignmenttargettype",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return CONST_invalid""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ~invalid~.""",
  )
}
