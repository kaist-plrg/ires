package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingList[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BindingList", 1, 0, Rhs(List(NonTerminal("BindingList", List(""), false), Terminal(","), NonTerminal("LexicalBinding", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-let-and-const-declarations-runtime-semantics-evaluation",
    "sec-let-and-const-declarations",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingList "Evaluation")
  |  0:let next = __x0__
  |  1:[? next]
  |  2:access __x1__ = (LexicalBinding "Evaluation")
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _next_ be the result of evaluating |BindingList|.""",
    """          1. ReturnIfAbrupt(_next_).""",
    """          1. Return the result of evaluating |LexicalBinding|.""",
  )
}
