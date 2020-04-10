package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._

object SubstitutionTemplate0ArgumentListEvaluation0 {
  val length: Int = 0
  val func: Func = parseFunc(""""SubstitutionTemplate0ArgumentListEvaluation0" (this, TemplateHead, Expression, TemplateSpans) => {
    let templateLiteral = this
    app __x0__ = (GetTemplateObject templateLiteral)
    let siteObj = __x0__
    access __x1__ = (Expression "Evaluation")
    let firstSubRef = __x1__
    app __x2__ = (GetValue firstSubRef)
    if (is-completion __x2__) if (= __x2__["Type"] CONST_normal) __x2__ = __x2__["Value"] else return __x2__ else {}
    let firstSub = __x2__
    access __x3__ = (TemplateSpans "SubstitutionEvaluation")
    let restSub = __x3__
    if (is-completion restSub) if (= restSub["Type"] CONST_normal) restSub = restSub["Value"] else return restSub else {}
    restSub
    let __x4__ = (copy-obj restSub)
    prepend firstSub -> __x4__
    prepend siteObj -> __x4__
    app __x5__ = (WrapCompletion __x4__)
    return __x5__
  }""")
}