package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[1,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 1, 0, Rhs(List(Terminal("{"), NonTerminal("BindingRestProperty", List(""), false), Terminal("}")), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:let excludedNames = (new [])
  |  1:access __x0__ = (BindingRestProperty "RestBindingInitialization" value environment excludedNames)
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _excludedNames_ be a new empty List.""",
    """        1. Return the result of performing RestBindingInitialization of |BindingRestProperty| with _value_, _environment_, and _excludedNames_ as the arguments.""",
  )
}
