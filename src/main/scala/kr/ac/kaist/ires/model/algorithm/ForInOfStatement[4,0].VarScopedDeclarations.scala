package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[4,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 4, 0, Rhs(List(Terminal("for"), Terminal("("), Terminal("var"), NonTerminal("ForBinding", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:let declarations = (new [ForBinding])
  |  1:access __x0__ = (Statement "VarScopedDeclarations")
  |  1:let __x1__ = __x0__
  |  1:let __x2__ = 0i
  |  1:while (< __x2__ __x1__.length) {
  |    let __x3__ = __x1__[__x2__]
  |    append __x3__ -> declarations
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  2:return declarations
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _declarations_ be a List whose sole element is |ForBinding|.""",
    """        1. Append to _declarations_ the elements of the VarScopedDeclarations of |Statement|.""",
    """        1. Return _declarations_.""",
  )
}
