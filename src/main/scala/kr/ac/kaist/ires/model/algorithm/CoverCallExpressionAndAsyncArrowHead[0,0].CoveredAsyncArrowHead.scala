package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverCallExpressionAndAsyncArrowHead[0,0].CoveredAsyncArrowHead` extends Algo {
  val head = SyntaxDirectedHead("CoverCallExpressionAndAsyncArrowHead", 0, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("Arguments", List(""), false)), None), "CoveredAsyncArrowHead", List())
  val ids = List(
    "sec-async-arrow-function-definitions-static-semantics-CoveredAsyncArrowHead",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return (parse-syntax CoverCallExpressionAndAsyncArrowHead "AsyncArrowHead" (new []))""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the |AsyncArrowHead| that is covered by |CoverCallExpressionAndAsyncArrowHead|.""",
  )
}
