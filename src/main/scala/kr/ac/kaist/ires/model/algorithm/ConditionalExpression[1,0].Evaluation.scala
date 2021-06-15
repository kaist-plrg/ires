package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConditionalExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ConditionalExpression", 1, 0, Rhs(List(NonTerminal("ShortCircuitExpression", List(""), false), Terminal("?"), NonTerminal("AssignmentExpression", List(""), false), Terminal(":"), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-conditional-operator-runtime-semantics-evaluation",
    "sec-conditional-operator",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ShortCircuitExpression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:app __x2__ = (ToBoolean [? __x1__])
  |  1:let lval = [! __x2__]
  |  5:if (= lval true) {
  |    3:access __x3__ = (AssignmentExpression0 "Evaluation")
  |    3:let trueRef = __x3__
  |    4:app __x4__ = (GetValue trueRef)
  |    4:return [? __x4__]
  |  } else {
  |    6:access __x5__ = (AssignmentExpression1 "Evaluation")
  |    6:let falseRef = __x5__
  |    7:app __x6__ = (GetValue falseRef)
  |    7:return [? __x6__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |ShortCircuitExpression|.""",
    """        1. Let _lval_ be ! ToBoolean(? GetValue(_lref_)).""",
    """        1. If _lval_ is *true*, then""",
    """          1. Let _trueRef_ be the result of evaluating the first |AssignmentExpression|.""",
    """          1. Return ? GetValue(_trueRef_).""",
    """        1. Else,""",
    """          1. Let _falseRef_ be the result of evaluating the second |AssignmentExpression|.""",
    """          1. Return ? GetValue(_falseRef_).""",
  )
}
