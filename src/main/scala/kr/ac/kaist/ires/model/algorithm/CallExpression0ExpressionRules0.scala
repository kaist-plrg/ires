package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object CallExpression0ExpressionRules0 {
  val length: Int = 0
  val func: Func = parseFunc(""""CallExpression0ExpressionRules0" (this, CoverCallExpressionAndAsyncArrowHead) => {
    if (= this call) {
      app __x0__ = (WrapCompletion true)
      return __x0__
    } else {}
    app __x1__ = (WrapCompletion false)
    return __x1__
  }""")
}