package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MultiplicativeExpression[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("MultiplicativeExpression", 1, 0, Rhs(List(NonTerminal("MultiplicativeExpression", List(""), false), NonTerminal("MultiplicativeOperator", List(""), false), NonTerminal("ExponentiationExpression", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-expression-rules",
    "sec-static-semantics-hascallintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *false*.""",
  )
}
