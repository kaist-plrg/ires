package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateMiddleList[0,0].SubstitutionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateMiddleList", 0, 0, Rhs(List(NonTerminal("TemplateMiddle", List(""), false), NonTerminal("Expression", List(""), false)), None), "SubstitutionEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-substitutionevaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let subRef = __x0__
  |  1:app __x1__ = (GetValue subRef)
  |  1:let sub = [? __x1__]
  |  2:return (new [sub])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _subRef_ be the result of evaluating |Expression|.""",
    """          1. Let _sub_ be ? GetValue(_subRef_).""",
    """          1. Return a List whose sole element is _sub_.""",
  )
}
