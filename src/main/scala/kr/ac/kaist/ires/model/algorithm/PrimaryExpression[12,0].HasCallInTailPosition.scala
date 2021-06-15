package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[12,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 12, 0, Rhs(List(NonTerminal("CoverParenthesizedExpressionAndArrowParameterList", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-expression-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverParenthesizedExpressionAndArrowParameterList "CoveredParenthesizedExpression")
  |  0:let expr = __x0__
  |  1:access __x1__ = (expr "HasCallInTailPosition" call)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be CoveredParenthesizedExpression of |CoverParenthesizedExpressionAndArrowParameterList|.""",
    """          1. Return HasCallInTailPosition of _expr_ with argument _call_.""",
  )
}
