package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object GetThisValue {
  val length: Int = 1
  val func: Func = parseFunc(""""GetThisValue" (V) => {
    app __x0__ = (IsPropertyReference V)
    assert (= __x0__ true)
    app __x1__ = (IsSuperReference V)
    if (= __x1__ true) {
      app __x2__ = (WrapCompletion V["thisValue"])
      return __x2__
    } else {}
    app __x3__ = (GetBase V)
    app __x4__ = (WrapCompletion __x3__)
    return __x4__
  }""")
}