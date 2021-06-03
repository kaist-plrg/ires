package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableDeclarationList[0,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("VariableDeclarationList", 0, 0, Rhs(List(NonTerminal("VariableDeclaration", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [VariableDeclaration])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a List whose sole element is |VariableDeclaration|.""",
  )
}
