package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnaryExpression[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UnaryExpression", 4, 0, Rhs(List(Terminal("+"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-unary-plus-operator-runtime-semantics-evaluation",
    "sec-unary-plus-operator",
    "sec-unary-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let expr = __x0__
  |  1:app __x1__ = (GetValue expr)
  |  1:app __x2__ = (ToNumber [? __x1__])
  |  1:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be the result of evaluating |UnaryExpression|.""",
    """          1. Return ? ToNumber(? GetValue(_expr_)).""",
  )
}
