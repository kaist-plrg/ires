package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object NewExpression1Evaluation0 {
  val length: Int = 0
  val func: Func = parseFunc(""""NewExpression1Evaluation0" (this, NewExpression) => {
    app __x0__ = (EvaluateNew NewExpression CONST_empty)
    if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
    app __x1__ = (WrapCompletion __x0__)
    return __x1__
  }""")
}