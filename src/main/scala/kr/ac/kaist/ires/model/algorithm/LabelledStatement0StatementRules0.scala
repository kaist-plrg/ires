package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object LabelledStatement0StatementRules0 {
  val length: Int = 0
  val func: Func = parseFunc(""""LabelledStatement0StatementRules0" (this, LabelIdentifier, LabelledItem) => {
    access __x0__ = (LabelledItem "HasCallInTailPosition")
    app __x1__ = (__x0__ call)
    app __x2__ = (WrapCompletion __x1__)
    return __x2__
  }""")
}