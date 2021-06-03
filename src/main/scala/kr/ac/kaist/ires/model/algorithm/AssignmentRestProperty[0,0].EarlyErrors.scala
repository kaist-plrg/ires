package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentRestProperty[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AssignmentRestProperty", 0, 0, Rhs(List(Terminal("..."), NonTerminal("DestructuringAssignmentTarget", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-destructuring-assignment-static-semantics-early-errors",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""if (|| (is-instance-of DestructuringAssignmentTarget ArrayLiteral) (is-instance-of DestructuringAssignmentTarget ObjectLiteral)) throw SyntaxError else 2:{}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if |DestructuringAssignmentTarget| is an |ArrayLiteral| or an |ObjectLiteral|.""",
    """          </li>""",
  )
}
