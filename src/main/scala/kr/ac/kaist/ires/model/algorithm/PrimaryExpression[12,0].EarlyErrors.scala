package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[12,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 12, 0, Rhs(List(NonTerminal("CoverParenthesizedExpressionAndArrowParameterList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-grouping-operator-static-semantics-early-errors",
    "sec-grouping-operator",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = false
  |  0:if (= absent (parse-syntax CoverParenthesizedExpressionAndArrowParameterList "ParenthesizedExpression" (new []))) __x0__ = true else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:??? "All Early Error rules for nt:{ParenthesizedExpression} and its derived productions also apply to CoveredParenthesizedExpression of nt:{CoverParenthesizedExpressionAndArrowParameterList} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if |CoverParenthesizedExpressionAndArrowParameterList| is not covering a |ParenthesizedExpression|.""",
    """          </li>""",
    """          <li>""",
    """            All Early Error rules for |ParenthesizedExpression| and its derived productions also apply to CoveredParenthesizedExpression of |CoverParenthesizedExpressionAndArrowParameterList|.""",
    """          </li>""",
  )
}
