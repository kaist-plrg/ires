package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object ObjectAssignmentPattern1DestructuringAssignmentEvaluation0 {
  val length: Int = 0
  val func: Func = parseFunc(""""ObjectAssignmentPattern1DestructuringAssignmentEvaluation0" (this, AssignmentRestProperty, value) => {
    app __x0__ = (RequireObjectCoercible value)
    if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
    __x0__
    let excludedNames = (new [])
    access __x1__ = (AssignmentRestProperty "RestDestructuringAssignmentEvaluation")
    app __x2__ = (__x1__ value excludedNames)
    app __x3__ = (WrapCompletion __x2__)
    return __x3__
  }""")
}