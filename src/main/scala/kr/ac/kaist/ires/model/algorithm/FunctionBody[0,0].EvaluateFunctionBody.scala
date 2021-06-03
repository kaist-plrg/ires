package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionBody[0,0].EvaluateFunctionBody` extends Algo {
  val head = SyntaxDirectedHead("FunctionBody", 0, 0, Rhs(List(NonTerminal("FunctionStatementList", List(""), false)), None), "EvaluateFunctionBody", List(Param("functionObject", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-runtime-semantics-evaluatefunctionbody",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (FunctionDeclarationInstantiation functionObject argumentsList)
  |  0:[? __x0__]
  |  1:access __x1__ = (FunctionStatementList "Evaluation")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? FunctionDeclarationInstantiation(_functionObject_, _argumentsList_).""",
    """        1. Return the result of evaluating |FunctionStatementList|.""",
  )
}
