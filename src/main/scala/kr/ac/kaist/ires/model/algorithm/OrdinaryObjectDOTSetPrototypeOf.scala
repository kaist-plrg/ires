package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object OrdinaryObjectDOTSetPrototypeOf {
  val length: Int = 1
  val func: Func = parseFunc(""""OrdinaryObject.SetPrototypeOf" (O, V) => {
    app __x0__ = (OrdinarySetPrototypeOf O V)
    if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
    app __x1__ = (WrapCompletion __x0__)
    return __x1__
  }""")
}