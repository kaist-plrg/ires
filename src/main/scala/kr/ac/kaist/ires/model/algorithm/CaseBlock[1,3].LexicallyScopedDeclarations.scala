package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[1,3].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 1, 3, Rhs(List(Terminal("{"), NonTerminal("CaseClauses", List(""), true), NonTerminal("DefaultClause", List(""), false), NonTerminal("CaseClauses", List(""), true), Terminal("}")), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= CaseClauses0 absent)) {
  |    access __x0__ = (CaseClauses0 "LexicallyScopedDeclarations")
  |    let declarations = __x0__
  |  } else let declarations = (new [])
  |  2:access __x1__ = (DefaultClause "LexicallyScopedDeclarations")
  |  2:let __x2__ = __x1__
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> declarations
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:if (= CaseClauses1 absent) return declarations else 1:{}
  |  4:??? "Return the result of appending to id:{declarations} the elements of the LexicallyScopedDeclarations of the second nt:{CaseClauses} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the first |CaseClauses| is present, let _declarations_ be the LexicallyScopedDeclarations of the first |CaseClauses|.""",
    """        1. Else, let _declarations_ be a new empty List.""",
    """        1. Append to _declarations_ the elements of the LexicallyScopedDeclarations of |DefaultClause|.""",
    """        1. If the second |CaseClauses| is not present, return _declarations_.""",
    """        1. Return the result of appending to _declarations_ the elements of the LexicallyScopedDeclarations of the second |CaseClauses|.""",
  )
}
