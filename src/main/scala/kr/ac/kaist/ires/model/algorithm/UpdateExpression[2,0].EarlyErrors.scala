package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UpdateExpression[2,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("UpdateExpression", 2, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), Terminal("--")), None), "EarlyErrors", List())
  val ids = List(
    "sec-update-expressions-static-semantics-early-errors",
    "sec-update-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "AssignmentTargetType")
  |  0:if (! (= __x0__ CONST_simple)) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is an early Syntax Error if AssignmentTargetType of |LeftHandSideExpression| is not ~simple~.""",
    """        </li>""",
  )
}
