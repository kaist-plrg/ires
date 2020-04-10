package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object CaseClauseIsSelected {
  val length: Int = 2
  val func: Func = parseFunc(""""CaseClauseIsSelected" (C, input) => {
    access __x0__ = (C "Expression")
    access __x1__ = (__x0__ "Evaluation")
    let exprRef = __x1__
    app __x2__ = (GetValue exprRef)
    if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
    let clauseSelector = __x2__
    app __x3__ = (StrictEqualityComparison input clauseSelector)
    app __x4__ = (WrapCompletion __x3__)
    return __x4__
  }""")
}