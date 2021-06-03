package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExpressionStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExpressionStatement", 0, 0, Rhs(List(NonTerminal("Expression", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-expression-statement-runtime-semantics-evaluation",
    "sec-expression-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:let exprRef = __x0__
  |  1:app __x1__ = (GetValue exprRef)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _exprRef_ be the result of evaluating |Expression|.""",
    """        1. Return ? GetValue(_exprRef_).""",
  )
}
