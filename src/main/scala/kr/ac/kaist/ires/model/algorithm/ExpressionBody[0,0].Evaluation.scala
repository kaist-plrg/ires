package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExpressionBody[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExpressionBody", 0, 0, Rhs(List(NonTerminal("AssignmentExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-arrow-function-definitions-runtime-semantics-evaluation",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let exprValue = [? __x1__]
  |  2:return (new Completion("Type" -> CONST_return, "Value" -> exprValue, "Target" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |AssignmentExpression|.""",
    """        1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """        1. Return Completion { [[Type]]: ~return~, [[Value]]: _exprValue_, [[Target]]: ~empty~ }.""",
  )
}
