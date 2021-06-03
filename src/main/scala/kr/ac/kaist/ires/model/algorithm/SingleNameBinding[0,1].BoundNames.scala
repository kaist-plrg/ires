package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SingleNameBinding[0,1].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("SingleNameBinding", 0, 1, Rhs(List(NonTerminal("BindingIdentifier", List(""), false), NonTerminal("Initializer", List(""), true)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the BoundNames of |BindingIdentifier|.""",
  )
}
