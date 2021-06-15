package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConciseBody[0,0].EvaluateBody` extends Algo {
  val head = SyntaxDirectedHead("ConciseBody", 0, 0, Rhs(List(NonTerminal("ExpressionBody", List(""), false)), None), "EvaluateBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluatebody",
    "sec-ecmascript-function-objects-call-thisargument-argumentslist",
    "sec-ecmascript-function-objects",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ConciseBody "EvaluateConciseBody" functionObject argumentsList)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? EvaluateConciseBody of |ConciseBody| with arguments _functionObject_ and _argumentsList_.""",
  )
}
