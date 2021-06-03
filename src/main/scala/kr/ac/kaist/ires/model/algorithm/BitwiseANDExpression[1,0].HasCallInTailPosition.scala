package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BitwiseANDExpression[1,0].HasCallInTailPosition` extends Algo {
  val head = SyntaxDirectedHead("BitwiseANDExpression", 1, 0, Rhs(List(NonTerminal("BitwiseANDExpression", List(""), false), Terminal("&"), NonTerminal("EqualityExpression", List(""), false)), None), "HasCallInTailPosition", List(Param("call", Normal)))
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
