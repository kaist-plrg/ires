package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionExpression[0,0].NamedEvaluation` extends Algo {
  val head = SyntaxDirectedHead("FunctionExpression", 0, 0, Rhs(List(Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "NamedEvaluation", List(Param("name", Normal)))
  val ids = List(
    "sec-runtime-semantics-namedevaluation",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionExpression "InstantiateOrdinaryFunctionExpression" name)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return InstantiateOrdinaryFunctionExpression of |FunctionExpression| with argument _name_.""",
  )
}
