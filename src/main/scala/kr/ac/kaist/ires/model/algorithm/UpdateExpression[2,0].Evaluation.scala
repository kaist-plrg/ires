package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UpdateExpression[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UpdateExpression", 2, 0, Rhs(List(NonTerminal("LeftHandSideExpression", List(""), false), Terminal("--")), None), "Evaluation", List())
  val ids = List(
    "sec-postfix-decrement-operator-runtime-semantics-evaluation",
    "sec-postfix-decrement-operator",
    "sec-update-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LeftHandSideExpression "Evaluation")
  |  0:let lhs = __x0__
  |  1:app __x1__ = (GetValue lhs)
  |  1:app __x2__ = (ToNumeric [? __x1__])
  |  1:let oldValue = [? __x2__]
  |  2:app __x3__ = (PRIMITIVE[(typeof oldValue)].subtract oldValue PRIMITIVE[(typeof oldValue)].unit)
  |  2:let newValue = [! __x3__]
  |  3:app __x4__ = (PutValue lhs newValue)
  |  3:[? __x4__]
  |  4:return oldValue
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _lhs_ be the result of evaluating |LeftHandSideExpression|.""",
    """          1. Let _oldValue_ be ? ToNumeric(? GetValue(_lhs_)).""",
    """          1. Let _newValue_ be ! Type(_oldValue_)::subtract(_oldValue_, Type(_oldValue_)::unit).""",
    """          1. Perform ? PutValue(_lhs_, _newValue_).""",
    """          1. Return _oldValue_.""",
  )
}
