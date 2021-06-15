package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConciseBody[0,0].EvaluateConciseBody` extends Algo {
  val head = SyntaxDirectedHead("ConciseBody", 0, 0, Rhs(List(NonTerminal("ExpressionBody", List(""), false)), None), "EvaluateConciseBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluateconcisebody",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (FunctionDeclarationInstantiation functionObject argumentsList)
  |  0:[? __x0__]
  |  1:access __x1__ = (ExpressionBody "Evaluation")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? FunctionDeclarationInstantiation(_functionObject_, _argumentsList_).""",
    """        1. Return the result of evaluating |ExpressionBody|.""",
  )
}
