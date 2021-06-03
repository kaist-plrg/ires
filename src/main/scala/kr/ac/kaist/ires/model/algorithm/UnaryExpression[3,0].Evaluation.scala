package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnaryExpression[3,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UnaryExpression", 3, 0, Rhs(List(Terminal("typeof"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-typeof-operator-runtime-semantics-evaluation",
    "sec-typeof-operator",
    "sec-unary-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let val = __x0__
  |  1:if (is-instance-of val ReferenceRecord) {
  |    2:app __x1__ = (IsUnresolvableReference val)
  |    2:if (= __x1__ true) return "undefined" else 3:{}
  |  } else 3:{}
  |  3:app __x2__ = (GetValue val)
  |  3:val = [? __x2__]
  |  4:??? "Return a String according to link:{unhandled: table-typeof-operator-results} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _val_ be the result of evaluating |UnaryExpression|.""",
    """          1. If _val_ is a Reference Record, then""",
    """            1. If IsUnresolvableReference(_val_) is *true*, return *"undefined"*.""",
    """          1. Set _val_ to ? GetValue(_val_).""",
    """          1. Return a String according to <emu-xref href="#table-typeof-operator-results"></emu-xref>.""",
  )
}
