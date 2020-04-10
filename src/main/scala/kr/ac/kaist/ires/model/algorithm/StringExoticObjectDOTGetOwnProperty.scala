package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object StringExoticObjectDOTGetOwnProperty {
  val length: Int = 1
  val func: Func = parseFunc(""""StringExoticObject.GetOwnProperty" (S, P) => {
    app __x0__ = (IsPropertyKey P)
    assert (= __x0__ true)
    app __x1__ = (OrdinaryGetOwnProperty S P)
    let desc = __x1__
    if (! (= desc undefined)) {
      app __x2__ = (WrapCompletion desc)
      return __x2__
    } else {}
    app __x3__ = (StringGetOwnProperty S P)
    if (is-completion __x3__) if (= __x3__["Type"] CONST_normal) __x3__ = __x3__["Value"] else return __x3__ else {}
    app __x4__ = (WrapCompletion __x3__)
    return __x4__
  }""")
}