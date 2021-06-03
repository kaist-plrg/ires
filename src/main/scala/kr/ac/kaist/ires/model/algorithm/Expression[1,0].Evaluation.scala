package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Expression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Expression", 1, 0, Rhs(List(NonTerminal("Expression", List(""), false), Terminal(","), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-comma-operator-runtime-semantics-evaluation",
    "sec-comma-operator",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let lref = __x0__
  |  1:app __x1__ = (GetValue lref)
  |  1:[? __x1__]
  |  2:access __x2__ = (AssignmentExpression "Evaluation")
  |  2:let rref = __x2__
  |  3:app __x3__ = (GetValue rref)
  |  3:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _lref_ be the result of evaluating |Expression|.""",
    """        1. Perform ? GetValue(_lref_).""",
    """        1. Let _rref_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Return ? GetValue(_rref_).""",
  )
}
