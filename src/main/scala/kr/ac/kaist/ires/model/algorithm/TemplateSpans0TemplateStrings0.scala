package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object TemplateSpans0TemplateStrings0 {
  val length: Int = 0
  val func: Func = parseFunc(""""TemplateSpans0TemplateStrings0" (this, TemplateTail, raw) => {
    if (= raw false) {
      access __x0__ = (TemplateTail "TV")
      let tail = __x0__
    } else {
      access __x1__ = (TemplateTail "TRV")
      let tail = __x1__
    }
    return (new [tail])
  }""")
}