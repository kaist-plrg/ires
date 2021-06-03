package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionStatementList[0,1].VarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("FunctionStatementList", 0, 1, Rhs(List(NonTerminal("StatementList", List(""), false)), None), "VarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-vardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "TopLevelVarDeclaredNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return TopLevelVarDeclaredNames of |StatementList|.""",
  )
}
