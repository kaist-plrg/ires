package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItem[1,0].LexicallyDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("ModuleItem", 1, 0, Rhs(List(NonTerminal("ExportDeclaration", List(""), false)), None), "LexicallyDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-lexicallydeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:??? "If nt:{ExportDeclaration} is code:{export} nt:{VariableStatement} , return a new empty List ."
  |  1:access __x0__ = (ExportDeclaration "BoundNames")
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |ExportDeclaration| is `export` |VariableStatement|, return a new empty List.""",
    """        1. Return the BoundNames of |ExportDeclaration|.""",
  )
}
