package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncMethod[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AsyncMethod", 0, 0, Rhs(List(Terminal("async"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
  val ids = List(
    "sec-async-function-definitions-static-semantics-early-errors",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:access __x1__ = (AsyncFunctionBody "FunctionBodyContainsUseStrict")
  |  0:__x0__ = (= __x1__ true)
  |  0:if __x0__ {
  |    access __x2__ = (UniqueFormalParameters "IsSimpleParameterList")
  |    __x0__ = (= __x2__ false)
  |  } else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:access __x3__ = (AsyncMethod "HasDirectSuper")
  |  1:if (= __x3__ true) throw SyntaxError else 2:{}
  |  2:access __x4__ = (UniqueFormalParameters "Contains" "AwaitExpression")
  |  2:if (= __x4__ true) throw SyntaxError else 2:{}
  |  3:access __x5__ = (UniqueFormalParameters "BoundNames")
  |  3:access __x6__ = (AsyncFunctionBody "LexicallyDeclaredNames")
  |  3:let __x7__ = __x5__
  |  3:let __x8__ = __x6__
  |  3:let __x9__ = 0i
  |  3:let __x10__ = 0i
  |  3:let __x11__ = false
  |  3:while (< __x9__ __x7__.length) {
  |    __x10__ = 0i
  |    while (< __x10__ __x8__.length) if (= __x7__[__x9__] __x8__[__x10__]) __x11__ = true else 2:{}
  |  }
  |  3:if __x11__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>It is a Syntax Error if FunctionBodyContainsUseStrict of |AsyncFunctionBody| is *true* and IsSimpleParameterList of |UniqueFormalParameters| is *false*.</li>""",
    """        <li>It is a Syntax Error if HasDirectSuper of |AsyncMethod| is *true*.</li>""",
    """        <li>It is a Syntax Error if |UniqueFormalParameters| Contains |AwaitExpression| is *true*.</li>""",
    """        <li>It is a Syntax Error if any element of the BoundNames of |UniqueFormalParameters| also occurs in the LexicallyDeclaredNames of |AsyncFunctionBody|.</li>""",
  )
}
