package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::YieldExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("YieldExpression", 1, 0, Rhs(List(Terminal("yield"), NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-generator-function-definitions-runtime-semantics-evaluation",
    "sec-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let value = [? __x1__]
  |  2:app __x2__ = (Yield value)
  |  2:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Let _value_ be ? GetValue(_exprRef_).""",
    """        1. Return ? Yield(_value_).""",
  )
}
