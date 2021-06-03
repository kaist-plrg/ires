package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[10,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 10, 0, Rhs(List(NonTerminal("RegularExpressionLiteral", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-primary-expression-regular-expression-literals-static-semantics-early-errors",
    "sec-primary-expression-regular-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsValidRegularExpressionLiteral RegularExpressionLiteral)
  |  0:if (= __x0__ false) throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          <li>""",
    """            It is a Syntax Error if IsValidRegularExpressionLiteral(|RegularExpressionLiteral|) is *false*.""",
    """          </li>""",
  )
}
