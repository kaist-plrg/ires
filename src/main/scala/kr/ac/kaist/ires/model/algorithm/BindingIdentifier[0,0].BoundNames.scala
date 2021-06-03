package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingIdentifier[0,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("BindingIdentifier", 0, 0, Rhs(List(NonTerminal("Identifier", List(""), false)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Identifier "StringValue")
  |  0:return (new [__x0__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a List whose sole element is the StringValue of |Identifier|.""",
  )
}
