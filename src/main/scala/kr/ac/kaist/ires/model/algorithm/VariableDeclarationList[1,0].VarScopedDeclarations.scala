package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableDeclarationList[1,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("VariableDeclarationList", 1, 0, Rhs(List(NonTerminal("VariableDeclarationList", List(""), false), Terminal(","), NonTerminal("VariableDeclaration", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (VariableDeclarationList "VarScopedDeclarations")
  |  0:let declarations = __x0__
  |  1:append VariableDeclaration -> declarations
  |  2:return declarations
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _declarations_ be VarScopedDeclarations of |VariableDeclarationList|.""",
    """        1. Append |VariableDeclaration| to _declarations_.""",
    """        1. Return _declarations_.""",
  )
}
