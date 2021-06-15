package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LogicalORExpression[1,0].AssignmentTargetType` extends Algo {
  val head = SyntaxDirectedHead("LogicalORExpression", 1, 0, Rhs(List(NonTerminal("LogicalORExpression", List(""), false), Terminal("||"), NonTerminal("LogicalANDExpression", List(""), false)), None), "AssignmentTargetType", List())
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
