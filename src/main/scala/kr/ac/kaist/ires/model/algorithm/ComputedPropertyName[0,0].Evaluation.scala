package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ComputedPropertyName[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ComputedPropertyName", 0, 0, Rhs(List(Terminal("["), NonTerminal("AssignmentExpression", List(""), false), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-object-initializer-runtime-semantics-evaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let exprValue = __x0__
  |  1:app __x1__ = (GetValue exprValue)
  |  1:let propName = [? __x1__]
  |  2:app __x2__ = (ToPropertyKey propName)
  |  2:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _exprValue_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _propName_ be ? GetValue(_exprValue_).""",
    """          1. Return ? ToPropertyKey(_propName_).""",
  )
}
