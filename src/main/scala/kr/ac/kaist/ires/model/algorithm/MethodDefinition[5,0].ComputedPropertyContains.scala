package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[5,0].ComputedPropertyContains` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 5, 0, Rhs(List(Terminal("set"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("PropertySetParameterList", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "ComputedPropertyContains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-computedpropertycontains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "ComputedPropertyContains" symbol)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of ComputedPropertyContains for |PropertyName| with argument _symbol_.""",
  )
}
