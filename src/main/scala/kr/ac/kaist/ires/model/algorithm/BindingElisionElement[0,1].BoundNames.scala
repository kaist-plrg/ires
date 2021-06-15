package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BindingElisionElement[0,1].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("BindingElisionElement", 0, 1, Rhs(List(NonTerminal("Elision", List(""), true), NonTerminal("BindingElement", List(""), false)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingElement "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return BoundNames of |BindingElement|.""",
  )
}
