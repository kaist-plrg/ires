package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[1,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 1, 0, Rhs(List(Terminal("yield")), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (InitializeBoundName "yield" value environment)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? InitializeBoundName(*"yield"*, _value_, _environment_).""",
  )
}
