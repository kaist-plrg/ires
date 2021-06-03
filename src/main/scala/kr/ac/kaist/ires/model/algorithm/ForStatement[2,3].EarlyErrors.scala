package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[2,3].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 2, 3, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("LexicalDeclaration", List(""), false), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-for-statement-static-semantics-early-errors",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LexicalDeclaration "BoundNames")
  |  0:access __x1__ = (Statement "VarDeclaredNames")
  |  0:let __x2__ = __x0__
  |  0:let __x3__ = __x1__
  |  0:let __x4__ = 0i
  |  0:let __x5__ = 0i
  |  0:let __x6__ = false
  |  0:while (< __x4__ __x2__.length) {
  |    __x5__ = 0i
  |    while (< __x5__ __x3__.length) if (= __x2__[__x4__] __x3__[__x5__]) __x6__ = true else 2:{}
  |  }
  |  0:if __x6__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if any element of the BoundNames of |LexicalDeclaration| also occurs in the VarDeclaredNames of |Statement|.""",
    """          </li>""",
  )
}
