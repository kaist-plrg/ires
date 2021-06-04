package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableStatement[0,0].VarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("VariableStatement", 0, 0, Rhs(List(Terminal("var"), NonTerminal("VariableDeclarationList", List(""), false), Terminal(";")), None), "VarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-vardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (VariableDeclarationList "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return BoundNames of |VariableDeclarationList|.""",
  )
}