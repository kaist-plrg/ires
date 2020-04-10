package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object PrimaryExpression12HasName0 {
  val length: Int = 0
  val func: Func = parseFunc(""""PrimaryExpression12HasName0" (this, CoverParenthesizedExpressionAndArrowParameterList) => {
    access __x0__ = (CoverParenthesizedExpressionAndArrowParameterList "CoveredParenthesizedExpression")
    let expr = __x0__
    access __x1__ = (expr "IsFunctionDefinition")
    if (= __x1__ false) return false else {}
    access __x2__ = (expr "HasName")
    return __x2__
  }""")
}