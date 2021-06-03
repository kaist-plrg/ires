package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalDeclaration[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("LexicalDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("BindingList", List(""), false), Terminal(";")), None), "EarlyErrors", List())
  val ids = List(
    "sec-let-and-const-declarations-static-semantics-early-errors",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingList "BoundNames")
  |  0:if (contains __x0__ "let") throw SyntaxError else 2:{}
  |  1:access __x1__ = (BindingList "BoundNames")
  |  1:app __x2__ = (IsDuplicate __x1__)
  |  1:if __x2__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the BoundNames of |BindingList| contains *"let"*.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if the BoundNames of |BindingList| contains any duplicate entries.""",
    """          </li>""",
  )
}
