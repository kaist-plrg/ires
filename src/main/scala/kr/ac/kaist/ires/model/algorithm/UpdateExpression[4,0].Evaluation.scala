package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UpdateExpression[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UpdateExpression", 4, 0, Rhs(List(Terminal("--"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-prefix-decrement-operator-runtime-semantics-evaluation",
    "sec-prefix-decrement-operator",
    "sec-update-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let expr = __x0__
  |  1:app __x1__ = (GetValue expr)
  |  1:app __x2__ = (ToNumeric [? __x1__])
  |  1:let oldValue = [? __x2__]
  |  2:app __x3__ = (PRIMITIVE[(typeof oldValue)].subtract oldValue PRIMITIVE[(typeof oldValue)].unit)
  |  2:let newValue = [! __x3__]
  |  3:app __x4__ = (PutValue expr newValue)
  |  3:[? __x4__]
  |  4:return newValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be the result of evaluating |UnaryExpression|.""",
    """          1. Let _oldValue_ be ? ToNumeric(? GetValue(_expr_)).""",
    """          1. Let _newValue_ be ! Type(_oldValue_)::subtract(_oldValue_, Type(_oldValue_)::unit).""",
    """          1. Perform ? PutValue(_expr_, _newValue_).""",
    """          1. Return _newValue_.""",
  )
}
