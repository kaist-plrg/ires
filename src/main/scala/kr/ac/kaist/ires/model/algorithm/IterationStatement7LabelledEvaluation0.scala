package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object IterationStatement7LabelledEvaluation0 {
  val length: Int = 0
  val func: Func = parseFunc(""""IterationStatement7LabelledEvaluation0" (this, ForDeclaration, Expression, Statement, labelSet) => {
    access __x0__ = (ForDeclaration "BoundNames")
    app __x1__ = (ForInOfHeadEvaluation __x0__ Expression CONST_enumerate)
    if (is-completion __x1__) if (= __x1__["Type"] CONST_normal) __x1__ = __x1__["Value"] else return __x1__ else {}
    let keyResult = __x1__
    app __x2__ = (ForInOfBodyEvaluation ForDeclaration Statement keyResult CONST_enumerate CONST_lexicalBinding labelSet)
    if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
    app __x3__ = (WrapCompletion __x2__)
    return __x3__
  }""")
}