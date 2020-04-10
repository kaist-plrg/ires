package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object MethodDefinition4HasDirectSuper0 {
  val length: Int = 0
  val func: Func = parseFunc(""""MethodDefinition4HasDirectSuper0" (this, PropertyName, FunctionBody) => {
    access __x0__ = (FunctionBody "Contains")
    app __x1__ = (__x0__ "SuperCall")
    return __x1__
  }""")
}