package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-arrow-function-definitions-static-semantics-early-errors",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ArrowParameters "Contains" "YieldExpression")
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |  1:access __x1__ = (ArrowParameters "Contains" "AwaitExpression")
  |  1:if (= __x1__ true) throw SyntaxError else 2:{}
  |  2:let __x2__ = true
  |  2:access __x3__ = (ConciseBody "ConciseBodyContainsUseStrict")
  |  2:__x2__ = (= __x3__ true)
  |  2:if __x2__ {
  |    access __x4__ = (ArrowParameters "IsSimpleParameterList")
  |    __x2__ = (= __x4__ false)
  |  } else 2:{}
  |  2:if __x2__ throw SyntaxError else 2:{}
  |  3:access __x5__ = (ArrowParameters "BoundNames")
  |  3:access __x6__ = (ConciseBody "LexicallyDeclaredNames")
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
    """        <li>""",
    """          It is a Syntax Error if |ArrowParameters| Contains |YieldExpression| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if |ArrowParameters| Contains |AwaitExpression| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if ConciseBodyContainsUseStrict of |ConciseBody| is *true* and IsSimpleParameterList of |ArrowParameters| is *false*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if any element of the BoundNames of |ArrowParameters| also occurs in the LexicallyDeclaredNames of |ConciseBody|.""",
    """        </li>""",
  )
}
