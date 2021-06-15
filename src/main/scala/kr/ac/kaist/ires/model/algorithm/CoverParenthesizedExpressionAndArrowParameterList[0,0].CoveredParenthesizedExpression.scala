package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverParenthesizedExpressionAndArrowParameterList[0,0].CoveredParenthesizedExpression` extends Algo {
  val head = SyntaxDirectedHead("CoverParenthesizedExpressionAndArrowParameterList", 0, 0, Rhs(List(Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")")), None), "CoveredParenthesizedExpression", List())
  val ids = List(
    "sec-static-semantics-coveredparenthesizedexpression",
    "sec-primary-expression-semantics",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return (parse-syntax CoverParenthesizedExpressionAndArrowParameterList "ParenthesizedExpression" (new []))""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the |ParenthesizedExpression| that is covered by |CoverParenthesizedExpressionAndArrowParameterList|.""",
  )
}
