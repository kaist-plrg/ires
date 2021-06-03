package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].LexicallyDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "LexicallyDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-lexicallydeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= CaseClauses0 absent)) {
  |    access __x0__ = (CaseClauses0 "LexicallyDeclaredNames")
  |    let names = __x0__
  |  } else let names = (new [])
  |  2:access __x1__ = (DefaultClause "LexicallyDeclaredNames")
  |  2:let __x2__ = __x1__
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:if (= CaseClauses1 absent) return names else 1:{}
  |  4:??? "Return the result of appending to id:{names} the elements of the LexicallyDeclaredNames of the second nt:{CaseClauses} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, let _names_ be the LexicallyDeclaredNames of the first |CaseClauses|.""",
    """        1. Else, let _names_ be a new empty List.""",
    """        1. Append to _names_ the elements of the LexicallyDeclaredNames of |DefaultClause|.""",
    """        1. If the second |CaseClauses| is not present, return _names_.""",
    """        1. Return the result of appending to _names_ the elements of the LexicallyDeclaredNames of the second |CaseClauses|.""",
  )
}
