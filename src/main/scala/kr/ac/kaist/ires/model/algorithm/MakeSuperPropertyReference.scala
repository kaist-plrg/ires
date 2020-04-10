package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object MakeSuperPropertyReference {
  val length: Int = 3
  val func: Func = parseFunc(""""MakeSuperPropertyReference" (actualThis, propertyKey, strict) => {
    app __x0__ = (GetThisEnvironment )
    let env = __x0__
    app __x1__ = (env["HasSuperBinding"] env)
    assert (= __x1__ true)
    app __x2__ = (env["GetSuperBase"] env)
    if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
    let baseValue = __x2__
    app __x3__ = (RequireObjectCoercible baseValue)
    if (is-completion __x3__) if (= __x3__["Type"] CONST_normal) __x3__ = __x3__["Value"] else return __x3__ else {}
    let bv = __x3__
    app __x4__ = (WrapCompletion (new Reference("BaseValue" -> bv, "ReferencedName" -> propertyKey, "thisValue" -> actualThis, "StrictReference" -> strict)))
    return __x4__
  }""")
}