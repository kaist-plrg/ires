package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionDeclaration[1,0].InstantiateFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("FunctionDeclaration", 1, 0, Rhs(List(Terminal("function"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "InstantiateFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiatefunctionobject",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionDeclaration "InstantiateOrdinaryFunctionObject" scope)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? InstantiateOrdinaryFunctionObject of |FunctionDeclaration| with argument _scope_.""",
  )
}
