package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoverCallExpressionAndAsyncArrowHead[0,0].CoveredCallExpression` extends Algo {
  val head = SyntaxDirectedHead("CoverCallExpressionAndAsyncArrowHead", 0, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("Arguments", List(""), false)), None), "CoveredCallExpression", List())
  val ids = List(
    "sec-left-hand-side-expressions-static-semantics-coveredcallexpression",
    "sec-static-semantics",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""return (parse-syntax CoverCallExpressionAndAsyncArrowHead "CallMemberExpression" (new []))""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the |CallMemberExpression| that is covered by |CoverCallExpressionAndAsyncArrowHead|.""",
  )
}
