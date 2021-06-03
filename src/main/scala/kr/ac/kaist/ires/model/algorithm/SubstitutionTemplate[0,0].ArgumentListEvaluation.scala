package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SubstitutionTemplate[0,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("SubstitutionTemplate", 0, 0, Rhs(List(NonTerminal("TemplateHead", List(""), false), NonTerminal("Expression", List(""), false), NonTerminal("TemplateSpans", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let firstSubRef = __x0__
  |  1:app __x1__ = (GetValue firstSubRef)
  |  1:let firstSub = [? __x1__]
  |  2:access __x2__ = (TemplateSpans "SubstitutionEvaluation")
  |  2:let restSub = [? __x2__]
  |  4:return (new [firstSub, restSub])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _firstSubRef_ be the result of evaluating |Expression|.""",
    """          1. Let _firstSub_ be ? GetValue(_firstSubRef_).""",
    """          1. Let _restSub_ be ? SubstitutionEvaluation of |TemplateSpans|.""",
    """          1. Assert: _restSub_ is a List.""",
    """          1. Return a List whose first element is _firstSub_ and whose subsequent elements are the elements of _restSub_. _restSub_ may contain no elements.""",
  )
}
