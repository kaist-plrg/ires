package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object MethodDefinition5ComputedPropertyContains0 {
  val length: Int = 0
  val func: Func = parseFunc(""""MethodDefinition5ComputedPropertyContains0" (this, PropertyName, PropertySetParameterList, FunctionBody, symbol) => {
    access __x0__ = (PropertyName "ComputedPropertyContains")
    app __x1__ = (__x0__ symbol)
    return __x1__
  }""")
}