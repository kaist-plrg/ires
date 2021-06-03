package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionDeclaration[0,0].InstantiateFunctionObject` extends Algo {
  val head = SyntaxDirectedHead("AsyncFunctionDeclaration", 0, 0, Rhs(List(Terminal("async"), Terminal("function"), NonTerminal("BindingIdentifier", List(""), false), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "InstantiateFunctionObject", List(Param("scope", Normal)))
  val ids = List(
    "sec-runtime-semantics-instantiatefunctionobject",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncFunctionDeclaration "InstantiateAsyncFunctionObject" scope)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? InstantiateAsyncFunctionObject of |AsyncFunctionDeclaration| with argument _scope_.""",
  )
}
