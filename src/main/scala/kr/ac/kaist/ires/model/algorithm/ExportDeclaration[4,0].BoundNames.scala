package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[4,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 4, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("HoistableDeclaration", List(""), false)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (HoistableDeclaration "BoundNames")
  |  0:let declarationNames = __x0__
  |  1:if (contains declarationNames "*default*") {} else append "*default*" -> declarationNames
  |  2:return declarationNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _declarationNames_ be the BoundNames of |HoistableDeclaration|.""",
    """        1. If _declarationNames_ does not include the element *"\*default\*"*, append *"\*default\*"* to _declarationNames_.""",
    """        1. Return _declarationNames_.""",
  )
}
