package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItem[1,0].VarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ModuleItem", 1, 0, Rhs(List(NonTerminal("ExportDeclaration", List(""), false)), None), "VarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-varscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:??? "If nt:{ExportDeclaration} is code:{export} nt:{VariableStatement} , return VarScopedDeclarations of nt:{VariableStatement} ."
  |  1:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |ExportDeclaration| is `export` |VariableStatement|, return VarScopedDeclarations of |VariableStatement|.""",
    """        1. Return a new empty List.""",
  )
}
