package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[5,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 5, 0, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("ForDeclaration", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-for-in-and-for-of-statements-static-semantics-early-errors",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ForDeclaration "BoundNames")
  |  0:if (contains __x0__ "let") throw SyntaxError else 2:{}
  |  1:access __x1__ = (ForDeclaration "BoundNames")
  |  1:access __x2__ = (Statement "VarDeclaredNames")
  |  1:let __x3__ = __x1__
  |  1:let __x4__ = __x2__
  |  1:let __x5__ = 0i
  |  1:let __x6__ = 0i
  |  1:let __x7__ = false
  |  1:while (< __x5__ __x3__.length) {
  |    __x6__ = 0i
  |    while (< __x6__ __x4__.length) if (= __x3__[__x5__] __x4__[__x6__]) __x7__ = true else 2:{}
  |  }
  |  1:if __x7__ throw SyntaxError else 2:{}
  |  2:access __x8__ = (ForDeclaration "BoundNames")
  |  2:app __x9__ = (IsDuplicate __x8__)
  |  2:if __x9__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if the BoundNames of |ForDeclaration| contains *"let"*.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if any element of the BoundNames of |ForDeclaration| also occurs in the VarDeclaredNames of |Statement|.""",
    """          </li>""",
    """          <li>""",
    """            It is a Syntax Error if the BoundNames of |ForDeclaration| contains any duplicate entries.""",
    """          </li>""",
  )
}
