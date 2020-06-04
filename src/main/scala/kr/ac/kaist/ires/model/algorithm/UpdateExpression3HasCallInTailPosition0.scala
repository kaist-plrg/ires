package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.Algorithm
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object UpdateExpression3HasCallInTailPosition0 extends Algorithm {
  val length: Int = 0
  val lang: Boolean = true
  val func: Func = parseFunc(""""UpdateExpression3HasCallInTailPosition0" (this, UnaryExpression, call) => {
    app __x0__ = (WrapCompletion false)
    return __x0__
  }""")
}