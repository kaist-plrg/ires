package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object FunctionEnvironmentRecordDOTGetThisBinding {
  val length: Int = 0
  val func: Func = parseFunc(""""FunctionEnvironmentRecord.GetThisBinding" (this) => {
    let envRec = this
    assert (! (= envRec["ThisBindingStatus"] "lexical"))
    if (= envRec["ThisBindingStatus"] "uninitialized") {
      app __x0__ = (ThrowCompletion (new OrdinaryObject("Prototype" -> INTRINSIC_ReferenceErrorPrototype, "ErrorData" -> undefined, "SubMap" -> (new SubMap()))))
      return __x0__
    } else {}
    app __x1__ = (WrapCompletion envRec["ThisValue"])
    return __x1__
  }""")
}