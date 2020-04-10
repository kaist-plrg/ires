package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object FunctionEnvironmentRecordDOTHasThisBinding {
  val length: Int = 0
  val func: Func = parseFunc(""""FunctionEnvironmentRecord.HasThisBinding" (this) => {
    let envRec = this
    if (= envRec["ThisBindingStatus"] "lexical") {
      app __x0__ = (WrapCompletion false)
      return __x0__
    } else {
      app __x1__ = (WrapCompletion true)
      return __x1__
    }
  }""")
}