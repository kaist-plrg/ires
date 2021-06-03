package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MultiplicativeExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("MultiplicativeExpression", 1, 0, Rhs(List(NonTerminal("MultiplicativeExpression", List(""), false), NonTerminal("MultiplicativeOperator", List(""), false), NonTerminal("ExponentiationExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-multiplicative-operators-runtime-semantics-evaluation",
    "sec-multiplicative-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let opText = (get-syntax MultiplicativeOperator)
  |  1:app __x0__ = (EvaluateStringOrNumericBinaryExpression MultiplicativeExpression opText ExponentiationExpression)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _opText_ be the source text matched by |MultiplicativeOperator|.""",
    """        1. Return ? EvaluateStringOrNumericBinaryExpression(|MultiplicativeExpression|, _opText_, |ExponentiationExpression|).""",
  )
}
