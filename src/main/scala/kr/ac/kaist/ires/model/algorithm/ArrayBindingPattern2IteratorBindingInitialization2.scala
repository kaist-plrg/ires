package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object ArrayBindingPattern2IteratorBindingInitialization2 {
  val length: Int = 0
  val func: Func = parseFunc(""""ArrayBindingPattern2IteratorBindingInitialization2" (this, BindingElementList, Elision, iteratorRecord, environment) => {
    access __x0__ = (BindingElementList "IteratorBindingInitialization")
    app __x1__ = (__x0__ iteratorRecord environment)
    if (is-completion __x1__) if (= __x1__["Type"] CONST_normal) __x1__ = __x1__["Value"] else return __x1__ else {}
    __x1__
    access __x2__ = (Elision "IteratorDestructuringAssignmentEvaluation")
    app __x3__ = (__x2__ iteratorRecord)
    app __x4__ = (WrapCompletion __x3__)
    return __x4__
  }""")
}