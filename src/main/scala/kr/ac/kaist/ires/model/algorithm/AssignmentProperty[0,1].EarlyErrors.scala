package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentProperty[0,1].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AssignmentProperty", 0, 1, Rhs(List(NonTerminal("IdentifierReference", List(""), false), NonTerminal("Initializer", List(""), true)), None), "EarlyErrors", List())
  val ids = List(
    "sec-destructuring-assignment-static-semantics-early-errors",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierReference "AssignmentTargetType")
  |  0:if (! (= __x0__ CONST_simple)) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if AssignmentTargetType of |IdentifierReference| is not ~simple~.""",
    """          </li>""",
  )
}
