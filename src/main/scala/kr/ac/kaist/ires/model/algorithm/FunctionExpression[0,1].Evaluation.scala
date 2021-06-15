package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionExpression[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("FunctionExpression", 0, 1, Rhs(List(Terminal("function"), NonTerminal("BindingIdentifier", List(""), true), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-function-definitions-runtime-semantics-evaluation",
    "sec-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionExpression "InstantiateOrdinaryFunctionExpression")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateOrdinaryFunctionExpression of |FunctionExpression|.""",
  )
}
