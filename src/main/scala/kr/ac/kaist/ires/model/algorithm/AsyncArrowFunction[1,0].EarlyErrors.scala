package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 1, 0, Rhs(List(NonTerminal("CoverCallExpressionAndAsyncArrowHead", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-async-arrow-function-definitions-static-semantics-early-errors",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverCallExpressionAndAsyncArrowHead "Contains" "YieldExpression")
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |  1:access __x1__ = (CoverCallExpressionAndAsyncArrowHead "Contains" "AwaitExpression")
  |  1:if (= __x1__ true) throw SyntaxError else 2:{}
  |  2:let __x2__ = false
  |  2:if (= absent (parse-syntax CoverCallExpressionAndAsyncArrowHead "AsyncArrowHead" (new []))) __x2__ = true else 2:{}
  |  2:if __x2__ throw SyntaxError else 2:{}
  |  3:access __x3__ = (CoverCallExpressionAndAsyncArrowHead "BoundNames")
  |  3:access __x4__ = (AsyncConciseBody "LexicallyDeclaredNames")
  |  3:let __x5__ = __x3__
  |  3:let __x6__ = __x4__
  |  3:let __x7__ = 0i
  |  3:let __x8__ = 0i
  |  3:let __x9__ = false
  |  3:while (< __x7__ __x5__.length) {
  |    __x8__ = 0i
  |    while (< __x8__ __x6__.length) if (= __x5__[__x7__] __x6__[__x8__]) __x9__ = true else 2:{}
  |  }
  |  3:if __x9__ throw SyntaxError else 2:{}
  |  4:let __x10__ = true
  |  4:access __x11__ = (AsyncConciseBody "AsyncConciseBodyContainsUseStrict")
  |  4:__x10__ = (= __x11__ true)
  |  4:if __x10__ {
  |    access __x12__ = (CoverCallExpressionAndAsyncArrowHead "IsSimpleParameterList")
  |    __x10__ = (= __x12__ false)
  |  } else 2:{}
  |  4:if __x10__ throw SyntaxError else 2:{}
  |  5:??? "All Early Error rules for nt:{AsyncArrowHead} and its derived productions apply to CoveredAsyncArrowHead of nt:{CoverCallExpressionAndAsyncArrowHead} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>It is a Syntax Error if |CoverCallExpressionAndAsyncArrowHead| Contains |YieldExpression| is *true*.</li>""",
    """        <li>It is a Syntax Error if |CoverCallExpressionAndAsyncArrowHead| Contains |AwaitExpression| is *true*.</li>""",
    """        <li>It is a Syntax Error if |CoverCallExpressionAndAsyncArrowHead| is not covering an |AsyncArrowHead|.</li>""",
    """        <li>It is a Syntax Error if any element of the BoundNames of |CoverCallExpressionAndAsyncArrowHead| also occurs in the LexicallyDeclaredNames of |AsyncConciseBody|.</li>""",
    """        <li>It is a Syntax Error if AsyncConciseBodyContainsUseStrict of |AsyncConciseBody| is *true* and IsSimpleParameterList of |CoverCallExpressionAndAsyncArrowHead| is *false*.</li>""",
    """        <li>All Early Error rules for |AsyncArrowHead| and its derived productions apply to CoveredAsyncArrowHead of |CoverCallExpressionAndAsyncArrowHead|.</li>""",
  )
}
