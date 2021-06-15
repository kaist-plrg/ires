package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BitwiseORExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BitwiseORExpression", 1, 0, Rhs(List(NonTerminal("BitwiseORExpression", List(""), false), Terminal("|"), NonTerminal("BitwiseXORExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-binary-bitwise-operators-runtime-semantics-evaluation",
    "sec-binary-bitwise-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateStringOrNumericBinaryExpression BitwiseORExpression "|" BitwiseXORExpression)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? EvaluateStringOrNumericBinaryExpression(|BitwiseORExpression|, `|`, |BitwiseXORExpression|).""",
  )
}
