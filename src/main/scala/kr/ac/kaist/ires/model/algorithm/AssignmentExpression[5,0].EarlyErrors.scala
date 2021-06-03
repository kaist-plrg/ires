package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentExpression[5,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AssignmentExpression", 5, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), NonTerminal("AssignmentOperator", List(""), false), NonTerminal("AssignmentExpression", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-assignment-operators-static-semantics-early-errors",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "AssignmentTargetType")
  |  0:if (! (= __x0__ CONST_simple)) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if AssignmentTargetType of |LeftHandSideExpression| is not ~simple~.""",
    """        </li>""",
  )
}
