package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 1, 0, Rhs(List(NonTerminal("CoverCallExpressionAndAsyncArrowHead", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-async-arrow-function-definitions-runtime-semantics-evaluation",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncArrowFunction "InstantiateAsyncArrowFunctionExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateAsyncArrowFunctionExpression of |AsyncArrowFunction|.""",
  )
}
