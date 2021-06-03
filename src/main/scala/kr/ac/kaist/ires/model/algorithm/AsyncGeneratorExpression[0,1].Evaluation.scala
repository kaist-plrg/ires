package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorExpression[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorExpression", 0, 1, Rhs(List(Terminal("async"), Terminal("function"), Terminal("*"), NonTerminal("BindingIdentifier", List(""), true), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncGeneratorBody", List(""), false), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-asyncgenerator-definitions-evaluation",
    "sec-async-generator-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncGeneratorExpression "InstantiateAsyncGeneratorFunctionExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateAsyncGeneratorFunctionExpression of |AsyncGeneratorExpression|.""",
  )
}
