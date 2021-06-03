package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItem[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ModuleItem", 0, 0, Rhs(List(NonTerminal("ImportDeclaration", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-imports-static-semantics-early-errors",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportDeclaration "BoundNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the BoundNames of |ImportDeclaration| contains any duplicate entries.""",
    """          </li>""",
  )
}
