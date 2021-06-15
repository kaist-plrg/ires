package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionDeclaration[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionDeclaration", 1, 0, Rhs(List(Terminal("async"), Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
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
  |    access __x2__ = (FormalParameters "IsSimpleParameterList")
  |    __x0__ = (= __x2__ false)
  |  } else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:access __x3__ = (FormalParameters "Contains" "AwaitExpression")
  |  1:if (= __x3__ true) throw SyntaxError else 2:{}
  |  2:??? "If the source code matching nt:{FormalParameters} is strict mode code , the Early Error rules for grammar:{UniqueFormalParameters0, [FormalParameters]} are applied ."
  |  3:if (&& (! (= BindingIdentifier absent)) true) {
  |    access __x4__ = (BindingIdentifier "StringValue")
  |    if (|| (= __x4__ "eval") (= __x4__ "arguments")) throw SyntaxError else 2:{}
  |  } else 2:{}
  |  4:access __x5__ = (FormalParameters "BoundNames")
  |  4:access __x6__ = (AsyncFunctionBody "LexicallyDeclaredNames")
  |  4:let __x7__ = __x5__
  |  4:let __x8__ = __x6__
  |  4:let __x9__ = 0i
  |  4:let __x10__ = 0i
  |  4:let __x11__ = false
  |  4:while (< __x9__ __x7__.length) {
  |    __x10__ = 0i
  |    while (< __x10__ __x8__.length) if (= __x7__[__x9__] __x8__[__x10__]) __x11__ = true else 2:{}
  |  }
  |  4:if __x11__ throw SyntaxError else 2:{}
  |  5:access __x12__ = (FormalParameters "Contains" "SuperProperty")
  |  5:if (= __x12__ true) throw SyntaxError else 2:{}
  |  6:access __x13__ = (AsyncFunctionBody "Contains" "SuperProperty")
  |  6:if (= __x13__ true) throw SyntaxError else 2:{}
  |  7:access __x14__ = (FormalParameters "Contains" "SuperCall")
  |  7:if (= __x14__ true) throw SyntaxError else 2:{}
  |  8:access __x15__ = (AsyncFunctionBody "Contains" "SuperCall")
  |  8:if (= __x15__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>It is a Syntax Error if FunctionBodyContainsUseStrict of |AsyncFunctionBody| is *true* and IsSimpleParameterList of |FormalParameters| is *false*.</li>""",
    """        <li>It is a Syntax Error if |FormalParameters| Contains |AwaitExpression| is *true*.</li>""",
    """        <li>If the source code matching |FormalParameters| is strict mode code, the Early Error rules for <emu-grammar>UniqueFormalParameters : FormalParameters</emu-grammar> are applied.</li>""",
    """        <li>If |BindingIdentifier| is present and the source code matching |BindingIdentifier| is strict mode code, it is a Syntax Error if the StringValue of |BindingIdentifier| is *"eval"* or *"arguments"*.</li>""",
    """        <li>It is a Syntax Error if any element of the BoundNames of |FormalParameters| also occurs in the LexicallyDeclaredNames of |AsyncFunctionBody|.</li>""",
    """        <li>It is a Syntax Error if |FormalParameters| Contains |SuperProperty| is *true*.</li>""",
    """        <li>It is a Syntax Error if |AsyncFunctionBody| Contains |SuperProperty| is *true*.</li>""",
    """        <li>It is a Syntax Error if |FormalParameters| Contains |SuperCall| is *true*.</li>""",
    """        <li>It is a Syntax Error if |AsyncFunctionBody| Contains |SuperCall| is *true*.</li>""",
  )
}
