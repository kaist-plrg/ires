package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[5,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 5, 0, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("ForDeclaration", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "VarScopedDeclarations")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the VarScopedDeclarations of |Statement|.""",
  )
}
