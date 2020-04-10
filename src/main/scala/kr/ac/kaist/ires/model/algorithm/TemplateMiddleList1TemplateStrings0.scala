package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object TemplateMiddleList1TemplateStrings0 {
  val length: Int = 0
  val func: Func = parseFunc(""""TemplateMiddleList1TemplateStrings0" (this, TemplateMiddleList, TemplateMiddle, Expression, raw) => {
    access __x0__ = (TemplateMiddleList "TemplateStrings")
    app __x1__ = (__x0__ raw)
    let front = __x1__
    if (= raw false) {
      access __x2__ = (TemplateMiddle "TV")
      let last = __x2__
    } else {
      access __x3__ = (TemplateMiddle "TRV")
      let last = __x3__
    }
    append last -> front
    return front
  }""")
}