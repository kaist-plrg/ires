package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DefaultClause[0,1].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("DefaultClause", 0, 1, Rhs(List(Terminal("default"), Terminal(":"), NonTerminal("StatementList", List(""), true)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= StatementList absent)) {
  |    access __x0__ = (StatementList "VarScopedDeclarations")
  |    return __x0__
  |  } else 1:{}
  |  1:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the |StatementList| is present, return the VarScopedDeclarations of |StatementList|.""",
    """        1. Return a new empty List.""",
  )
}
