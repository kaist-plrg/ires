package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-arrow-function-definitions-runtime-semantics-evaluation",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ArrowFunction "InstantiateArrowFunctionExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateArrowFunctionExpression of |ArrowFunction|.""",
  )
}
