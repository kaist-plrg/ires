package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverCallExpressionAndAsyncArrowHead[0,0].IsSimpleParameterList` extends Algo {
  val head = SyntaxDirectedHead("CoverCallExpressionAndAsyncArrowHead", 0, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("Arguments", List(""), false)), None), "IsSimpleParameterList", List())
  val ids = List(
    "sec-static-semantics-issimpleparameterlist",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverCallExpressionAndAsyncArrowHead "CoveredAsyncArrowHead")
  |  0:let head = __x0__
  |  1:access __x1__ = (head "IsSimpleParameterList")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _head_ be CoveredAsyncArrowHead of |CoverCallExpressionAndAsyncArrowHead|.""",
    """        1. Return IsSimpleParameterList of _head_.""",
  )
}
