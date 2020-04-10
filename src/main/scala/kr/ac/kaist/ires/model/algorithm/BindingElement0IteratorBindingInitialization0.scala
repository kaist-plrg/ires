package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object BindingElement0IteratorBindingInitialization0 {
  val length: Int = 0
  val func: Func = parseFunc(""""BindingElement0IteratorBindingInitialization0" (this, SingleNameBinding, iteratorRecord, environment) => {
    access __x0__ = (SingleNameBinding "IteratorBindingInitialization")
    app __x1__ = (__x0__ iteratorRecord environment)
    app __x2__ = (WrapCompletion __x1__)
    return __x2__
  }""")
}