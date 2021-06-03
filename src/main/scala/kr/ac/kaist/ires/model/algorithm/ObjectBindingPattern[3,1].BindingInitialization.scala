package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[3,1].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 3, 1, Rhs(List(Terminal("{"), NonTerminal("BindingPropertyList", List(""), false), Terminal(","), NonTerminal("BindingRestProperty", List(""), false), Terminal("}")), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "PropertyBindingInitialization" value environment)
  |  0:let excludedNames = [? __x0__]
  |  1:access __x1__ = (BindingRestProperty "RestBindingInitialization" value environment excludedNames)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _excludedNames_ be ? PropertyBindingInitialization of |BindingPropertyList| with arguments _value_ and _environment_.""",
    """        1. Return the result of performing RestBindingInitialization of |BindingRestProperty| with arguments _value_, _environment_, and _excludedNames_.""",
  )
}
