package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ThrowStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ThrowStatement", 0, 0, Rhs(List(Terminal("throw"), NonTerminal("Expression", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-throw-statement-runtime-semantics-evaluation",
    "sec-throw-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:let exprValue = [? __x1__]
  |  2:app __x2__ = (ThrowCompletion exprValue)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """        1. Let _exprValue_ be ? GetValue(_exprRef_).""",
    """        1. Return ThrowCompletion(_exprValue_).""",
  )
}
