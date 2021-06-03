package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementListItem[1,0].TopLevelVarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("StatementListItem", 1, 0, Rhs(List(NonTerminal("Declaration", List(""), false)), None), "TopLevelVarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevelvarscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of Declaration Declaration0) {
  |    access HoistableDeclaration = (Declaration "HoistableDeclaration")
  |    1:access __x0__ = (HoistableDeclaration "DeclarationPart")
  |    1:let declaration = __x0__
  |    2:return (new [declaration])
  |  } else 1:{}
  |  3:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Declaration| is <emu-grammar>Declaration : HoistableDeclaration</emu-grammar> , then""",
    """          1. Let _declaration_ be DeclarationPart of |HoistableDeclaration|.""",
    """          1. Return « _declaration_ ».""",
    """        1. Return a new empty List.""",
  )
}
