package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateMiddleList[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateMiddleList", 0, 0, Rhs(List(NonTerminal("TemplateMiddle", List(""), false), NonTerminal("Expression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-template-literals-runtime-semantics-evaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddle "TV")
  |  0:let head = __x0__
  |  1:access __x1__ = (Expression "Evaluation")
  |  1:let subRef = __x1__
  |  2:app __x2__ = (GetValue subRef)
  |  2:let sub = [? __x2__]
  |  3:app __x3__ = (ToString sub)
  |  3:let middle = [? __x3__]
  |  4:return (+ head middle)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _head_ be the TV of |TemplateMiddle| as defined in <emu-xref href="#sec-template-literal-lexical-components"></emu-xref>.""",
    """          1. Let _subRef_ be the result of evaluating |Expression|.""",
    """          1. Let _sub_ be ? GetValue(_subRef_).""",
    """          1. Let _middle_ be ? ToString(_sub_).""",
    """          1. Return the string-concatenation of _head_ and _middle_.""",
  )
}
