package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object GetReferencedName {
  val length: Int = 1
  val func: Func = parseFunc(""""GetReferencedName" (V) => {
    app __x0__ = (Type V)
    assert (= __x0__ Reference)
    app __x1__ = (WrapCompletion V["ReferencedName"])
    return __x1__
  }""")
}