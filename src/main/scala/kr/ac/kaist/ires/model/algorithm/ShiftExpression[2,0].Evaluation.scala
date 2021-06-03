package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ShiftExpression[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ShiftExpression", 2, 0, Rhs(List(NonTerminal("ShiftExpression", List(""), false), Terminal(">>"), NonTerminal("AdditiveExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-signed-right-shift-operator-runtime-semantics-evaluation",
    "sec-signed-right-shift-operator",
    "sec-bitwise-shift-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateStringOrNumericBinaryExpression ShiftExpression ">>" AdditiveExpression)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? EvaluateStringOrNumericBinaryExpression(|ShiftExpression|, `>>`, |AdditiveExpression|).""",
  )
}
