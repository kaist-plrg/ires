package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CallExpression[4,0].AssignmentTargetType` extends Algo {
  val head = SyntaxDirectedHead("CallExpression", 4, 0, Rhs(List(NonTerminal("CallExpression", List(""), false), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "AssignmentTargetType", List())
  val ids = List(
    "sec-static-semantics-assignmenttargettype",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return CONST_simple""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ~simple~.""",
  )
}
