package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnaryExpression[6,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UnaryExpression", 6, 0, Rhs(List(Terminal("~"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-bitwise-not-operator-runtime-semantics-evaluation",
    "sec-bitwise-not-operator",
    "sec-unary-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let expr = __x0__
  |  1:app __x1__ = (GetValue expr)
  |  1:app __x2__ = (ToNumeric [? __x1__])
  |  1:let oldValue = [? __x2__]
  |  2:let T = (typeof oldValue)
  |  3:app __x3__ = (PRIMITIVE[T].bitwiseNOT oldValue)
  |  3:return [! __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be the result of evaluating |UnaryExpression|.""",
    """          1. Let _oldValue_ be ? ToNumeric(? GetValue(_expr_)).""",
    """          1. Let _T_ be Type(_oldValue_).""",
    """          1. Return ! _T_::bitwiseNOT(_oldValue_).""",
  )
}
