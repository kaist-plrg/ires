package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LogicalANDExpression[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("LogicalANDExpression", 1, 0, Rhs(List(NonTerminal("LogicalANDExpression", List(""), false), Terminal("&&"), NonTerminal("BitwiseORExpression", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-expression-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BitwiseORExpression "HasCallInTailPosition" call)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return HasCallInTailPosition of |BitwiseORExpression| with argument _call_.""",
  )
}
