package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object IteratorStep {
  val length: Int = 1
  val func: Func = parseFunc(""""IteratorStep" (iteratorRecord) => {
    app __x0__ = (IteratorNext iteratorRecord)
    if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
    let result = __x0__
    app __x1__ = (IteratorComplete result)
    if (is-completion __x1__) if (= __x1__["Type"] CONST_normal) __x1__ = __x1__["Value"] else return __x1__ else {}
    let done = __x1__
    if (= done true) {
      app __x2__ = (WrapCompletion false)
      return __x2__
    } else {}
    app __x3__ = (WrapCompletion result)
    return __x3__
  }""")
}