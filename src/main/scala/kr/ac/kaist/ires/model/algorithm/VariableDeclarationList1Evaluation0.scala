package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object VariableDeclarationList1Evaluation0 {
  val length: Int = 0
  val func: Func = parseFunc(""""VariableDeclarationList1Evaluation0" (this, VariableDeclarationList, VariableDeclaration) => {
    access __x0__ = (VariableDeclarationList "Evaluation")
    let next = __x0__
    if (is-completion next) if (= next["Type"] CONST_normal) next = next["Value"] else return next else {}
    next
    access __x1__ = (VariableDeclaration "Evaluation")
    app __x2__ = (WrapCompletion __x1__)
    return __x2__
  }""")
}