package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverCallExpressionAndAsyncArrowHead[0,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("CoverCallExpressionAndAsyncArrowHead", 0, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("Arguments", List(""), false)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverCallExpressionAndAsyncArrowHead "CoveredAsyncArrowHead")
  |  0:let head = __x0__
  |  1:access __x1__ = (head "BoundNames")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _head_ be CoveredAsyncArrowHead of |CoverCallExpressionAndAsyncArrowHead|.""",
    """        1. Return the BoundNames of _head_.""",
  )
}
