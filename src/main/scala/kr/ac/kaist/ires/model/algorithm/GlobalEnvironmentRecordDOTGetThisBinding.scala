package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object GlobalEnvironmentRecordDOTGetThisBinding {
  val length: Int = 0
  val func: Func = parseFunc(""""GlobalEnvironmentRecord.GetThisBinding" (this) => {
    let envRec = this
    app __x0__ = (WrapCompletion envRec["GlobalThisValue"])
    return __x0__
  }""")
}