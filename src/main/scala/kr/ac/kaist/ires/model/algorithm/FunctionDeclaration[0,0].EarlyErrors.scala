package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclaration[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("FunctionDeclaration", 0, 0, Rhs(List(Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "EarlyErrors", List())
  val ids = List(
    "sec-function-definitions-static-semantics-early-errors",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:??? "If the source code matching nt:{FormalParameters} is strict mode code , the Early Error rules for grammar:{UniqueFormalParameters0, [FormalParameters]} are applied ."
  |  1:if (&& (! (= BindingIdentifier absent)) true) {
  |    access __x0__ = (BindingIdentifier "StringValue")
  |    if (|| (= __x0__ "eval") (= __x0__ "arguments")) throw SyntaxError else 2:{}
  |  } else 2:{}
  |  2:let __x1__ = true
  |  2:access __x2__ = (FunctionBody "FunctionBodyContainsUseStrict")
  |  2:__x1__ = (= __x2__ true)
  |  2:if __x1__ {
  |    access __x3__ = (FormalParameters "IsSimpleParameterList")
  |    __x1__ = (= __x3__ false)
  |  } else 2:{}
  |  2:if __x1__ throw SyntaxError else 2:{}
  |  3:access __x4__ = (FormalParameters "BoundNames")
  |  3:access __x5__ = (FunctionBody "LexicallyDeclaredNames")
  |  3:let __x6__ = __x4__
  |  3:let __x7__ = __x5__
  |  3:let __x8__ = 0i
  |  3:let __x9__ = 0i
  |  3:let __x10__ = false
  |  3:while (< __x8__ __x6__.length) {
  |    __x9__ = 0i
  |    while (< __x9__ __x7__.length) if (= __x6__[__x8__] __x7__[__x9__]) __x10__ = true else 2:{}
  |  }
  |  3:if __x10__ throw SyntaxError else 2:{}
  |  4:access __x11__ = (FormalParameters "Contains" "SuperProperty")
  |  4:if (= __x11__ true) throw SyntaxError else 2:{}
  |  5:access __x12__ = (FunctionBody "Contains" "SuperProperty")
  |  5:if (= __x12__ true) throw SyntaxError else 2:{}
  |  6:access __x13__ = (FormalParameters "Contains" "SuperCall")
  |  6:if (= __x13__ true) throw SyntaxError else 2:{}
  |  7:access __x14__ = (FunctionBody "Contains" "SuperCall")
  |  7:if (= __x14__ true) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          If the source code matching |FormalParameters| is strict mode code, the Early Error rules for <emu-grammar>UniqueFormalParameters : FormalParameters</emu-grammar> are applied.""",
    """        </li>""",
    """        <li>""",
    """          If |BindingIdentifier| is present and the source code matching |BindingIdentifier| is strict mode code, it is a Syntax Error if the StringValue of |BindingIdentifier| is *"eval"* or *"arguments"*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if FunctionBodyContainsUseStrict of |FunctionBody| is *true* and IsSimpleParameterList of |FormalParameters| is *false*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |FormalParameters| also occurs in the LexicallyDeclaredNames of |FunctionBody|.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |FormalParameters| Contains |SuperProperty| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |FunctionBody| Contains |SuperProperty| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |FormalParameters| Contains |SuperCall| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |FunctionBody| Contains |SuperCall| is *true*.""",
    """        </li>""",
  )
}
