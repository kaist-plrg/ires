package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BitwiseANDExpression[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BitwiseANDExpression", 1, 0, Rhs(List(NonTerminal("BitwiseANDExpression", List(""), false), Terminal("&"), NonTerminal("EqualityExpression", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-binary-bitwise-operators-runtime-semantics-evaluation",
    "sec-binary-bitwise-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (EvaluateStringOrNumericBinaryExpression BitwiseANDExpression "&" EqualityExpression)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? EvaluateStringOrNumericBinaryExpression(|BitwiseANDExpression|, `&`, |EqualityExpression|).""",
  )
}
