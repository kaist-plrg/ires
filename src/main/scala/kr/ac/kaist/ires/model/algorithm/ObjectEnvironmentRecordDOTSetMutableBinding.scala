package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object ObjectEnvironmentRecordDOTSetMutableBinding {
  val length: Int = 3
  val func: Func = parseFunc(""""ObjectEnvironmentRecord.SetMutableBinding" (this, N, V, S) => {
    let envRec = this
    let bindings = envRec["BindingObject"]
    app __x0__ = (Set bindings N V S)
    if (is-completion __x0__) if (= __x0__["Type"] CONST_normal) __x0__ = __x0__["Value"] else return __x0__ else {}
    app __x1__ = (WrapCompletion __x0__)
    return __x1__
  }""")
}