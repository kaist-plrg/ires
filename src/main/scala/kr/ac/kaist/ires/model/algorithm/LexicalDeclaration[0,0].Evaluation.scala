package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalDeclaration[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LexicalDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("BindingList", List(""), false), Terminal(";")), None), "Evaluation", List())
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
  |  2:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _next_ be the result of evaluating |BindingList|.""",
    """          1. ReturnIfAbrupt(_next_).""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
