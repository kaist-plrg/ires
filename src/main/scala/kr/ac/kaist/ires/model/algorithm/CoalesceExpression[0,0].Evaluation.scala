package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoalesceExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("CoalesceExpression", 0, 0, Rhs(List(NonTerminal("CoalesceExpressionHead", List(""), false), Terminal("??"), NonTerminal("BitwiseORExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-binary-logical-operators-runtime-semantics-evaluation",
    "sec-binary-logical-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoalesceExpressionHead "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:let lval = [? __x1__]
  |  5:if (|| (= lval undefined) (= lval null)) {
  |    3:access __x2__ = (BitwiseORExpression "Evaluation")
  |    3:let rref = __x2__
  |    4:app __x3__ = (GetValue rref)
  |    4:return [? __x3__]
  |  } else return lval
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |CoalesceExpressionHead|.""",
    """        1. Let _lval_ be ? GetValue(_lref_).""",
    """        1. If _lval_ is *undefined* or *null*, then""",
    """          1. Let _rref_ be the result of evaluating |BitwiseORExpression|.""",
    """          1. Return ? GetValue(_rref_).""",
    """        1. Otherwise, return _lval_.""",
  )
}
