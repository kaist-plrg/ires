package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayBindingPattern[0,3].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ArrayBindingPattern", 0, 3, Rhs(List(Terminal("["), NonTerminal("Elision", List(""), true), NonTerminal("BindingRestElement", List(""), false), Terminal("]")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingRestElement "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the BoundNames of |BindingRestElement|.""",
  )
}
