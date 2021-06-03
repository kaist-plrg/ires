package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[1,0].SubstitutionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateTail", List(""), false)), None), "SubstitutionEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-substitutionevaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "SubstitutionEvaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of SubstitutionEvaluation of |TemplateMiddleList|.""",
  )
}
