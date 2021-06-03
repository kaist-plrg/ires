package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 0, 0, Rhs(List(NonTerminal("TemplateTail", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-template-literals-runtime-semantics-evaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateTail "TV")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the TV of |TemplateTail| as defined in <emu-xref href="#sec-template-literal-lexical-components"></emu-xref>.""",
  )
}
