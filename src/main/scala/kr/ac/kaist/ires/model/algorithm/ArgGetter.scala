package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object ArgGetter {
  val length: Int = 2
  val func: Func = parseFunc(""""ArgGetter" (_0, _1, _2, f) => {
    let f = GLOBAL_context["Function"]
    let name = f["Name"]
    let env = f["Env"]
    app __x0__ = (env["GetBindingValue"] env name false)
    app __x1__ = (WrapCompletion __x0__)
    return __x1__
  }""")
}