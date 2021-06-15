package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExponentiationExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExponentiationExpression", 1, 0, Rhs(List(NonTerminal("UpdateExpression", List(""), false), Terminal("**"), NonTerminal("ExponentiationExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-exp-operator-runtime-semantics-evaluation",
    "sec-exp-operator",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateStringOrNumericBinaryExpression UpdateExpression "**" ExponentiationExpression)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? EvaluateStringOrNumericBinaryExpression(|UpdateExpression|, `**`, |ExponentiationExpression|).""",
  )
}
