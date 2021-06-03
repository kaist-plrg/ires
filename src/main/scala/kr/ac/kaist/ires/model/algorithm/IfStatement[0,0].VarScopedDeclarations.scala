package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IfStatement[0,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("IfStatement", 0, 0, Rhs(List(Terminal("if"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false), Terminal("else"), NonTerminal("Statement", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement0 "VarScopedDeclarations")
  |  0:let declarations = __x0__
  |  1:access __x1__ = (Statement1 "VarScopedDeclarations")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> declarations
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return declarations
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _declarations_ be VarScopedDeclarations of the first |Statement|.""",
    """        1. Append to _declarations_ the elements of the VarScopedDeclarations of the second |Statement|.""",
    """        1. Return _declarations_.""",
  )
}
