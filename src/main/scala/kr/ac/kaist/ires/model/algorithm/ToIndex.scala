package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object ToIndex {
  val length: Int = 1
  val func: Func = parseFunc(""""ToIndex" (value) => {
    if (= value undefined) let index = 0i else {
      app __x0__ = (ToInteger value)
      if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
      let integerIndex = __x0__
      if (< integerIndex 0i) {
        app __x1__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_RangeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
        return __x1__
      } else {}
      app __x2__ = (ToLength integerIndex)
      if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
      let index = __x2__
      app __x3__ = (SameValueZero integerIndex index)
      if (= __x3__ false) {
        app __x4__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_RangeErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
        return __x4__
      } else {}
    }
    app __x5__ = (WrapCompletion index)
    return __x5__
  }""")
}