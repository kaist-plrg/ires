package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Script[0,1].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("Script", 0, 1, Rhs(List(NonTerminal("ScriptBody", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-scripts-static-semantics-early-errors",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ScriptBody "LexicallyDeclaredNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:access __x2__ = (ScriptBody "LexicallyDeclaredNames")
  |  1:access __x3__ = (ScriptBody "VarDeclaredNames")
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
    """          It is a Syntax Error if the LexicallyDeclaredNames of |ScriptBody| contains any duplicate entries.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the LexicallyDeclaredNames of |ScriptBody| also occurs in the VarDeclaredNames of |ScriptBody|.""",
    """        </li>""",
  )
}
