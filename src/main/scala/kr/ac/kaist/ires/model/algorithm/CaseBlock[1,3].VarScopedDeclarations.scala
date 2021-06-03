package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= CaseClauses0 absent)) {
  |    access __x0__ = (CaseClauses0 "VarScopedDeclarations")
  |    let declarations = __x0__
  |  } else let declarations = (new [])
  |  2:access __x1__ = (DefaultClause "VarScopedDeclarations")
  |  2:let __x2__ = __x1__
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> declarations
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:if (= CaseClauses1 absent) return declarations else 1:{}
  |  4:access decls = (CaseClauses1 "VarScopedDeclarations")
  |  4:let __i__ = 0i
  |  4:while (< __i__ decls.length) {
  |    append decls[__i__] -> declarations
  |    __i__ = (+ __i__ 1i)
  |  }
  |  4:return decls
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, let _declarations_ be the VarScopedDeclarations of the first |CaseClauses|.""",
    """        1. Else, let _declarations_ be a new empty List.""",
    """        1. Append to _declarations_ the elements of the VarScopedDeclarations of |DefaultClause|.""",
    """        1. If the second |CaseClauses| is not present, return _declarations_.""",
    """        1. Return the result of appending to _declarations_ the elements of the VarScopedDeclarations of the second |CaseClauses|.""",
  )
}
