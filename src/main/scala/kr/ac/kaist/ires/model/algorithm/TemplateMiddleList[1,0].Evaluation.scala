package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TemplateMiddleList[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TemplateMiddleList", 1, 0, Rhs(List(NonTerminal("TemplateMiddleList", List(""), false), NonTerminal("TemplateMiddle", List(""), false), NonTerminal("Expression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-template-literals-runtime-semantics-evaluation",
    "sec-template-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (TemplateMiddleList "Evaluation")
  |  0:let rest = __x0__
  |  1:[? rest]
  |  2:access __x1__ = (TemplateMiddle "TV")
  |  2:let middle = __x1__
  |  3:access __x2__ = (Expression "Evaluation")
  |  3:let subRef = __x2__
  |  4:app __x3__ = (GetValue subRef)
  |  4:let sub = [? __x3__]
  |  5:app __x4__ = (ToString sub)
  |  5:let last = [? __x4__]
  |  6:return (+ (+ rest middle) last)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _rest_ be the result of evaluating |TemplateMiddleList|.""",
    """          1. ReturnIfAbrupt(_rest_).""",
    """          1. Let _middle_ be the TV of |TemplateMiddle| as defined in <emu-xref href="#sec-template-literal-lexical-components"></emu-xref>.""",
    """          1. Let _subRef_ be the result of evaluating |Expression|.""",
    """          1. Let _sub_ be ? GetValue(_subRef_).""",
    """          1. Let _last_ be ? ToString(_sub_).""",
    """          1. Return the string-concatenation of _rest_, _middle_, and _last_.""",
  )
}
