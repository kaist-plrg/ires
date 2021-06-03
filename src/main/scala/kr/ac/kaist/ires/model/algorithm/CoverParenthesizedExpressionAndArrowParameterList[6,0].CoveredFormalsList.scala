package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverParenthesizedExpressionAndArrowParameterList[6,0].CoveredFormalsList` extends Algo {
  val head = SyntaxDirectedHead("CoverParenthesizedExpressionAndArrowParameterList", 6, 0, Rhs(List(Terminal("("), NonTerminal("Expression", List(""), false), Terminal(","), Terminal("..."), NonTerminal("BindingPattern", List(""), false), Terminal(")")), None), "CoveredFormalsList", List())
  val ids = List(
    "sec-static-semantics-coveredformalslist",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return (parse-syntax CoverParenthesizedExpressionAndArrowParameterList "ArrowFormalParameters" (new []))""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the |ArrowFormalParameters| that is covered by |CoverParenthesizedExpressionAndArrowParameterList|.""",
  )
}
