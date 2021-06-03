package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IfStatement[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("IfStatement", 1, 0, Rhs(List(Terminal("if"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-if-statement-runtime-semantics-evaluation",
    "sec-if-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:app __x2__ = (ToBoolean [? __x1__])
  |  1:let exprValue = [! __x2__]
  |  4:if (= exprValue false) return undefined else {
  |    5:access __x3__ = (Statement "Evaluation")
  |    5:let stmtCompletion = __x3__
  |    6:app __x4__ = (UpdateEmpty stmtCompletion undefined)
  |    6:return __x4__
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """        1. Let _exprValue_ be ! ToBoolean(? GetValue(_exprRef_)).""",
    """        1. If _exprValue_ is *false*, then""",
    """          1. Return NormalCompletion(*undefined*).""",
    """        1. Else,""",
    """          1. Let _stmtCompletion_ be the result of evaluating |Statement|.""",
    """          1. Return Completion(UpdateEmpty(_stmtCompletion_, *undefined*)).""",
  )
}
