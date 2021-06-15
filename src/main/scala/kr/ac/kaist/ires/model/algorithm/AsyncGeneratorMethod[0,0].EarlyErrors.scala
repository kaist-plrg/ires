package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorMethod[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorMethod", 0, 0, Rhs(List(Terminal("async"), Terminal("*"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("UniqueFormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncGeneratorBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
  val ids = List(
    "sec-async-generator-function-definitions-static-semantics-early-errors",
    "sec-async-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncGeneratorMethod "HasDirectSuper")
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |  1:access __x1__ = (UniqueFormalParameters "Contains" "YieldExpression")
  |  1:if (= __x1__ true) throw SyntaxError else 2:{}
  |  2:access __x2__ = (UniqueFormalParameters "Contains" "AwaitExpression")
  |  2:if (= __x2__ true) throw SyntaxError else 2:{}
  |  3:let __x3__ = true
  |  3:access __x4__ = (AsyncGeneratorBody "FunctionBodyContainsUseStrict")
  |  3:__x3__ = (= __x4__ true)
  |  3:if __x3__ {
  |    access __x5__ = (UniqueFormalParameters "IsSimpleParameterList")
  |    __x3__ = (= __x5__ false)
  |  } else 2:{}
  |  3:if __x3__ throw SyntaxError else 2:{}
  |  4:access __x6__ = (UniqueFormalParameters "BoundNames")
  |  4:access __x7__ = (AsyncGeneratorBody "LexicallyDeclaredNames")
  |  4:let __x8__ = __x6__
  |  4:let __x9__ = __x7__
  |  4:let __x10__ = 0i
  |  4:let __x11__ = 0i
  |  4:let __x12__ = false
  |  4:while (< __x10__ __x8__.length) {
  |    __x11__ = 0i
  |    while (< __x11__ __x9__.length) if (= __x8__[__x10__] __x9__[__x11__]) __x12__ = true else 2:{}
  |  }
  |  4:if __x12__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>It is a Syntax Error if HasDirectSuper of |AsyncGeneratorMethod| is *true*.</li>""",
    """        <li>It is a Syntax Error if |UniqueFormalParameters| Contains |YieldExpression| is *true*.</li>""",
    """        <li>It is a Syntax Error if |UniqueFormalParameters| Contains |AwaitExpression| is *true*.</li>""",
    """        <li>It is a Syntax Error if FunctionBodyContainsUseStrict of |AsyncGeneratorBody| is *true* and IsSimpleParameterList of |UniqueFormalParameters| is *false*.</li>""",
    """        <li>It is a Syntax Error if any element of the BoundNames of |UniqueFormalParameters| also occurs in the LexicallyDeclaredNames of |AsyncGeneratorBody|.</li>""",
  )
}
