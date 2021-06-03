package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LiteralPropertyName[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("LiteralPropertyName", 2, 0, Rhs(List(NonTerminal("NumericLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-object-initializer-runtime-semantics-evaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (NumericLiteral "NumericValue")
  |  0:let nbr = __x0__
  |  1:app __x1__ = (ToString nbr)
  |  1:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _nbr_ be the NumericValue of |NumericLiteral|.""",
    """          1. Return ! ToString(_nbr_).""",
  )
}
