package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[0,0].SubstitutionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 0, 0, Rhs(List(NonTerminal("TemplateTail", List(""), false)), None), "SubstitutionEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-substitutionevaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a new empty List.""",
  )
}
