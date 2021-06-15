package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[3,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 3, 0, Rhs(List(Terminal("{"), NonTerminal("BindingPropertyList", List(""), false), Terminal(","), Terminal("}")), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "PropertyBindingInitialization" value environment)
  |  0:[? __x0__]
  |  1:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Perform ? PropertyBindingInitialization for |BindingPropertyList| using _value_ and _environment_ as the arguments.""",
    """        1. Return NormalCompletion(~empty~).""",
  )
}
