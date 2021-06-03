package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[2,0].BindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 2, 0, Rhs(List(Terminal("await")), None), "BindingInitialization", List(Param("value", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-bindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (InitializeBoundName "await" value environment)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ? InitializeBoundName(*"await"*, _value_, _environment_).""",
  )
}
