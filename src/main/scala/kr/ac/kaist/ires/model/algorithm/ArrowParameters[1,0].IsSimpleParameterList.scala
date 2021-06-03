package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowParameters[1,0].IsSimpleParameterList` extends Algo {
  val head = SyntaxDirectedHead("ArrowParameters", 1, 0, Rhs(List(NonTerminal("CoverParenthesizedExpressionAndArrowParameterList", List(""), false)), None), "IsSimpleParameterList", List())
  val ids = List(
    "sec-static-semantics-issimpleparameterlist",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverParenthesizedExpressionAndArrowParameterList "CoveredFormalsList")
  |  0:let formals = __x0__
  |  1:access __x1__ = (formals "IsSimpleParameterList")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _formals_ be CoveredFormalsList of |CoverParenthesizedExpressionAndArrowParameterList|.""",
    """        1. Return IsSimpleParameterList of _formals_.""",
  )
}
