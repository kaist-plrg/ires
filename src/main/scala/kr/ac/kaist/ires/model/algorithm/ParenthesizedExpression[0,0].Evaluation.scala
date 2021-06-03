package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ParenthesizedExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ParenthesizedExpression", 0, 0, Rhs(List(Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")")), None), "Evaluation", List())
  val ids = List(
    "sec-grouping-operator-runtime-semantics-evaluation",
    "sec-grouping-operator",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Expression "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of evaluating |Expression|. This may be of type Reference.""",
  )
}
