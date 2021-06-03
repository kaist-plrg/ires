package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionBody[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("FunctionBody", 0, 0, Rhs(List(NonTerminal("FunctionStatementList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-function-definitions-static-semantics-early-errors",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionStatementList "LexicallyDeclaredNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:access __x2__ = (FunctionStatementList "LexicallyDeclaredNames")
  |  1:access __x3__ = (FunctionStatementList "VarDeclaredNames")
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
  |  2:access __x9__ = (FunctionStatementList "ContainsDuplicateLabels" (new []))
  |  2:if (= __x9__ true) throw SyntaxError else 2:{}
  |  3:access __x10__ = (FunctionStatementList "ContainsUndefinedBreakTarget" (new []))
  |  3:if (= __x10__ true) throw SyntaxError else 2:{}
  |  4:access __x11__ = (FunctionStatementList "ContainsUndefinedContinueTarget" (new []) (new []))
  |  4:if (= __x11__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if the LexicallyDeclaredNames of |FunctionStatementList| contains any duplicate entries.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the LexicallyDeclaredNames of |FunctionStatementList| also occurs in the VarDeclaredNames of |FunctionStatementList|.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsDuplicateLabels of |FunctionStatementList| with argument « » is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsUndefinedBreakTarget of |FunctionStatementList| with argument « » is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ContainsUndefinedContinueTarget of |FunctionStatementList| with arguments « » and « » is *true*.""",
    """        </li>""",
  )
}
