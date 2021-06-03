package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowParameters[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ArrowParameters", 1, 0, Rhs(List(NonTerminal("CoverParenthesizedExpressionAndArrowParameterList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-arrow-function-definitions-static-semantics-early-errors",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = false
  |  0:if (= absent (parse-syntax CoverParenthesizedExpressionAndArrowParameterList "ArrowFormalParameters" (new []))) __x0__ = true else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:??? "All early error rules for nt:{ArrowFormalParameters} and its derived productions also apply to CoveredFormalsList of nt:{CoverParenthesizedExpressionAndArrowParameterList} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if |CoverParenthesizedExpressionAndArrowParameterList| is not covering an |ArrowFormalParameters|.""",
    """        </li>""",
    """        <li>""",
    """          All early error rules for |ArrowFormalParameters| and its derived productions also apply to CoveredFormalsList of |CoverParenthesizedExpressionAndArrowParameterList|.""",
    """        </li>""",
  )
}
