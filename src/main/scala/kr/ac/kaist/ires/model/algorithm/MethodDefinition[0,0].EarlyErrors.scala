package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 0, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
  val ids = List(
    "sec-method-definitions-static-semantics-early-errors",
    "sec-method-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:access __x1__ = (FunctionBody "FunctionBodyContainsUseStrict")
  |  0:__x0__ = (= __x1__ true)
  |  0:if __x0__ {
  |    access __x2__ = (UniqueFormalParameters "IsSimpleParameterList")
  |    __x0__ = (= __x2__ false)
  |  } else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:access __x3__ = (UniqueFormalParameters "BoundNames")
  |  1:access __x4__ = (FunctionBody "LexicallyDeclaredNames")
  |  1:let __x5__ = __x3__
  |  1:let __x6__ = __x4__
  |  1:let __x7__ = 0i
  |  1:let __x8__ = 0i
  |  1:let __x9__ = false
  |  1:while (< __x7__ __x5__.length) {
  |    __x8__ = 0i
  |    while (< __x8__ __x6__.length) if (= __x5__[__x7__] __x6__[__x8__]) __x9__ = true else 2:{}
  |  }
  |  1:if __x9__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if FunctionBodyContainsUseStrict of |FunctionBody| is *true* and IsSimpleParameterList of |UniqueFormalParameters| is *false*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |UniqueFormalParameters| also occurs in the LexicallyDeclaredNames of |FunctionBody|.""",
    """        </li>""",
  )
}
