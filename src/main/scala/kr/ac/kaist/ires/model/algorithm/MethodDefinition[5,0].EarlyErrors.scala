package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[5,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 5, 0, Rhs(List(Terminal("set"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("PropertySetParameterList", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
  val ids = List(
    "sec-method-definitions-static-semantics-early-errors",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertySetParameterList "BoundNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |  1:let __x2__ = true
  |  1:access __x3__ = (FunctionBody "FunctionBodyContainsUseStrict")
  |  1:__x2__ = (= __x3__ true)
  |  1:if __x2__ {
  |    access __x4__ = (PropertySetParameterList "IsSimpleParameterList")
  |    __x2__ = (= __x4__ false)
  |  } else 2:{}
  |  1:if __x2__ throw SyntaxError else 2:{}
  |  2:access __x5__ = (PropertySetParameterList "BoundNames")
  |  2:access __x6__ = (FunctionBody "LexicallyDeclaredNames")
  |  2:let __x7__ = __x5__
  |  2:let __x8__ = __x6__
  |  2:let __x9__ = 0i
  |  2:let __x10__ = 0i
  |  2:let __x11__ = false
  |  2:while (< __x9__ __x7__.length) {
  |    __x10__ = 0i
  |    while (< __x10__ __x8__.length) if (= __x7__[__x9__] __x8__[__x10__]) __x11__ = true else 2:{}
  |  }
  |  2:if __x11__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if BoundNames of |PropertySetParameterList| contains any duplicate elements.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if FunctionBodyContainsUseStrict of |FunctionBody| is *true* and IsSimpleParameterList of |PropertySetParameterList| is *false*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |PropertySetParameterList| also occurs in the LexicallyDeclaredNames of |FunctionBody|.""",
    """        </li>""",
  )
}
