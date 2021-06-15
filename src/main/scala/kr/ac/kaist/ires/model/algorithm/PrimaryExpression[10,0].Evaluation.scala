package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[10,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 10, 0, Rhs(List(NonTerminal("RegularExpressionLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-regular-expression-literals-runtime-semantics-evaluation",
    "sec-primary-expression-regular-expression-literals",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (RegularExpressionLiteral "BodyText")
  |  0:app __x1__ = (CodePointsToString __x0__)
  |  0:let pattern = [! __x1__]
  |  1:access __x2__ = (RegularExpressionLiteral "FlagText")
  |  1:app __x3__ = (CodePointsToString __x2__)
  |  1:let flags = [! __x3__]
  |  2:app __x4__ = (RegExpCreate pattern flags)
  |  2:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _pattern_ be ! CodePointsToString(BodyText of |RegularExpressionLiteral|).""",
    """          1. Let _flags_ be ! CodePointsToString(FlagText of |RegularExpressionLiteral|).""",
    """          1. Return RegExpCreate(_pattern_, _flags_).""",
  )
}
