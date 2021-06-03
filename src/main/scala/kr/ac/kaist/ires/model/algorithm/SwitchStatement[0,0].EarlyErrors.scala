package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SwitchStatement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("SwitchStatement", 0, 0, Rhs(List(Terminal("switch"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("CaseBlock", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-switch-statement-static-semantics-early-errors",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CaseBlock "LexicallyDeclaredNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:access __x2__ = (CaseBlock "LexicallyDeclaredNames")
  |  1:access __x3__ = (CaseBlock "VarDeclaredNames")
  |  1:let __x4__ = __x2__
  |  1:let __x5__ = __x3__
  |  1:let __x6__ = 0i
  |  1:let __x7__ = 0i
  |  1:let __x8__ = false
  |  1:while (< __x6__ __x4__.length) {
  |    __x7__ = 0i
  |    while (< __x7__ __x5__.length) if (= __x4__[__x6__] __x5__[__x7__]) __x8__ = true else 2:{}
  |  }
  |  1:if __x8__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if the LexicallyDeclaredNames of |CaseBlock| contains any duplicate entries.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the LexicallyDeclaredNames of |CaseBlock| also occurs in the VarDeclaredNames of |CaseBlock|.""",
    """        </li>""",
  )
}
