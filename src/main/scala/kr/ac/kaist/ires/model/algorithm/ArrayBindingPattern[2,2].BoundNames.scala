package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[2,2].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 2, 2, Rhs(List(Terminal("["), NonTerminal("BindingElementList", List(""), false), Terminal(","), NonTerminal("Elision", List(""), true), Terminal("]")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElementList "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the BoundNames of |BindingElementList|.""",
  )
}
