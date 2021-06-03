package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementList[1,0].TopLevelVarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("StatementList", 1, 0, Rhs(List(NonTerminal("StatementList", List(""), false), NonTerminal("StatementListItem", List(""), false)), None), "TopLevelVarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevelvarscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "TopLevelVarScopedDeclarations")
  |  0:let declarations = __x0__
  |  1:access __x1__ = (StatementListItem "TopLevelVarScopedDeclarations")
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
    """        1. Let _declarations_ be TopLevelVarScopedDeclarations of |StatementList|.""",
    """        1. Append to _declarations_ the elements of the TopLevelVarScopedDeclarations of |StatementListItem|.""",
    """        1. Return _declarations_.""",
  )
}
