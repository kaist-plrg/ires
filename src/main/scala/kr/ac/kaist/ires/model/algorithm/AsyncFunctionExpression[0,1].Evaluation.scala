package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionExpression[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionExpression", 0, 1, Rhs(List(Terminal("async"), Terminal("function"), NonTerminal("BindingIdentifier", List(""), true), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-async-function-definitions-runtime-semantics-evaluation",
    "sec-async-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncFunctionExpression "InstantiateAsyncFunctionExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateAsyncFunctionExpression of |AsyncFunctionExpression|.""",
  )
}
