package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AdditiveExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AdditiveExpression", 1, 0, Rhs(List(NonTerminal("AdditiveExpression", List(""), false), Terminal("+"), NonTerminal("MultiplicativeExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-addition-operator-plus-runtime-semantics-evaluation",
    "sec-addition-operator-plus",
    "sec-additive-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateStringOrNumericBinaryExpression AdditiveExpression "+" MultiplicativeExpression)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? EvaluateStringOrNumericBinaryExpression(|AdditiveExpression|, `+`, |MultiplicativeExpression|).""",
  )
}
