package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object OrdinaryPreventExtensions {
  val length: Int = 1
  val func: Func = parseFunc(""""OrdinaryPreventExtensions" (O) => {
    O["Extensible"] = false
    app __x0__ = (WrapCompletion true)
    return __x0__
  }""")
}