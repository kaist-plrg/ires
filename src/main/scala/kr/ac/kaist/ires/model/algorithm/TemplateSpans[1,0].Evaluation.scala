package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateSpans[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateSpans", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateTail", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-template-literals-runtime-semantics-evaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "Evaluation")
  |  0:let head = __x0__
  |  1:[? head]
  |  2:access __x1__ = (TemplateTail "TV")
  |  2:let tail = __x1__
  |  3:return (+ head tail)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _head_ be the result of evaluating |TemplateMiddleList|.""",
    """          1. ReturnIfAbrupt(_head_).""",
    """          1. Let _tail_ be the TV of |TemplateTail| as defined in <emu-xref href="#sec-template-literal-lexical-components"></emu-xref>.""",
    """          1. Return the string-concatenation of _head_ and _tail_.""",
  )
}
