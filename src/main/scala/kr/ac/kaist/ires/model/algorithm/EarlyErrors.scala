package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EarlyErrors` extends Algo {
  val head = NormalHead("EarlyErrors", List())
  val ids = List(
    "sec-for-in-and-for-of-statements-static-semantics-early-errors",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "AssignmentTargetType")
  |  0:if (! (= __x0__ CONST_simple)) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if AssignmentTargetType of |LeftHandSideExpression| is not ~simple~.""",
    """          </li>""",
  )
}
