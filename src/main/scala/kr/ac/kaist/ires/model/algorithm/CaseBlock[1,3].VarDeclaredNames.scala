package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].VarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "VarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-vardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= CaseClauses0 absent)) {
  |    access __x0__ = (CaseClauses0 "VarDeclaredNames")
  |    let names = __x0__
  |  } else let names = (new [])
  |  2:access __x1__ = (DefaultClause "VarDeclaredNames")
  |  2:let __x2__ = __x1__
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:if (= CaseClauses1 absent) return names else 1:{}
  |  4:access __names__ = (CaseClauses1 "VarDeclaredNames")
  |  4:let __i__ = 0i
  |  4:while (< __i__ __names__.length) {
  |    append __names__[__i__] -> names
  |    __i__ = (+ __i__ 1i)
  |  }
  |  4:return names
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, let _names_ be the VarDeclaredNames of the first |CaseClauses|.""",
    """        1. Else, let _names_ be a new empty List.""",
    """        1. Append to _names_ the elements of the VarDeclaredNames of |DefaultClause|.""",
    """        1. If the second |CaseClauses| is not present, return _names_.""",
    """        1. Return the result of appending to _names_ the elements of the VarDeclaredNames of the second |CaseClauses|.""",
  )
}
