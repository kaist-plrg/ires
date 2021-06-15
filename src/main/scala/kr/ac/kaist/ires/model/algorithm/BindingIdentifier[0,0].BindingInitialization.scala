package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[0,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 0, 0, Rhs(List(NonTerminal("Identifier", List(""), false)), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Identifier "StringValue")
  |  0:let name = __x0__
  |  1:app __x1__ = (InitializeBoundName name value environment)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _name_ be StringValue of |Identifier|.""",
    """        1. Return ? InitializeBoundName(_name_, _value_, _environment_).""",
  )
}
