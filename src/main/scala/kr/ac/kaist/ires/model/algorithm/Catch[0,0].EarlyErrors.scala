package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Catch[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("Catch", 0, 0, Rhs(List(Terminal("catch"), Terminal("("), NonTerminal("CatchParameter", List(""), false), Terminal(")"), NonTerminal("Block", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-try-statement-static-semantics-early-errors",
    "sec-try-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CatchParameter "BoundNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:access __x2__ = (CatchParameter "BoundNames")
  |  1:access __x3__ = (Block "LexicallyDeclaredNames")
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
  |  2:access __x9__ = (CatchParameter "BoundNames")
  |  2:access __x10__ = (Block "VarDeclaredNames")
  |  2:let __x11__ = __x9__
  |  2:let __x12__ = __x10__
  |  2:let __x13__ = 0i
  |  2:let __x14__ = 0i
  |  2:let __x15__ = false
  |  2:while (< __x13__ __x11__.length) {
  |    __x14__ = 0i
  |    while (< __x14__ __x12__.length) if (= __x11__[__x13__] __x12__[__x14__]) __x15__ = true else 2:{}
  |  }
  |  2:if __x15__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if BoundNames of |CatchParameter| contains any duplicate elements.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |CatchParameter| also occurs in the LexicallyDeclaredNames of |Block|.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |CatchParameter| also occurs in the VarDeclaredNames of |Block|.""",
    """        </li>""",
  )
}
