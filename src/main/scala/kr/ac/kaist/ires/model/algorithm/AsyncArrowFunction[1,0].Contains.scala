package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[1,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 1, 0, Rhs(List(NonTerminal("CoverCallExpressionAndAsyncArrowHead", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (|| (|| (|| (is-instance-of symbol NewTarget) (is-instance-of symbol SuperProperty)) (is-instance-of symbol SuperCall)) (= symbol "super")) (= symbol "this"))) return false else 1:{}
  |  1:access __x0__ = (CoverCallExpressionAndAsyncArrowHead "CoveredAsyncArrowHead")
  |  1:let head = __x0__
  |  2:access __x1__ = (head "Contains" symbol)
  |  2:if (= __x1__ true) return true else 1:{}
  |  3:access __x2__ = (AsyncConciseBody "Contains" symbol)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is not one of |NewTarget|, |SuperProperty|, |SuperCall|, `super`, or `this`, return *false*.""",
    """        1. Let _head_ be CoveredAsyncArrowHead of |CoverCallExpressionAndAsyncArrowHead|.""",
    """        1. If _head_ Contains _symbol_ is *true*, return *true*.""",
    """        1. Return |AsyncConciseBody| Contains _symbol_.""",
  )
}
