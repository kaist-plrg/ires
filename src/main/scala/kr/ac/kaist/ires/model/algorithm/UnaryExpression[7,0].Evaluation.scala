package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnaryExpression[7,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("UnaryExpression", 7, 0, Rhs(List(Terminal("!"), NonTerminal("UnaryExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-logical-not-operator-runtime-semantics-evaluation",
    "sec-logical-not-operator",
    "sec-unary-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (UnaryExpression "Evaluation")
  |  0:let expr = __x0__
  |  1:app __x1__ = (GetValue expr)
  |  1:app __x2__ = (ToBoolean [? __x1__])
  |  1:let oldValue = [! __x2__]
  |  2:if (= oldValue true) return false else 3:{}
  |  3:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be the result of evaluating |UnaryExpression|.""",
    """          1. Let _oldValue_ be ! ToBoolean(? GetValue(_expr_)).""",
    """          1. If _oldValue_ is *true*, return *false*.""",
    """          1. Return *true*.""",
  )
}
