package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateLiteral[0,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateLiteral", 0, 0, Rhs(List(NonTerminal("NoSubstitutionTemplate", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let templateLiteral = this
  |  1:app __x0__ = (GetTemplateObject templateLiteral)
  |  1:let siteObj = __x0__
  |  2:return (new [siteObj])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _templateLiteral_ be this |TemplateLiteral|.""",
    """          1. Let _siteObj_ be GetTemplateObject(_templateLiteral_).""",
    """          1. Return a List whose sole element is _siteObj_.""",
  )
}
