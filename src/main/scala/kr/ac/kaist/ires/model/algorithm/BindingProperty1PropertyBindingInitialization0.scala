package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object BindingProperty1PropertyBindingInitialization0 {
  val length: Int = 0
  val func: Func = parseFunc(""""BindingProperty1PropertyBindingInitialization0" (this, PropertyName, BindingElement, value, environment) => {
    access __x0__ = (PropertyName "Evaluation")
    let P = __x0__
    if (is-completion P) if (= P["Type"] CONST_normal) P = P["Value"] else return P else {}
    P
    access __x1__ = (BindingElement "KeyedBindingInitialization")
    app __x2__ = (__x1__ value environment P)
    if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
    __x2__
    app __x3__ = (WrapCompletion (new [P]))
    return __x3__
  }""")
}