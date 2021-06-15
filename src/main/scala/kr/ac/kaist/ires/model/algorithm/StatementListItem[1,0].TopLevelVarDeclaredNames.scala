package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementListItem[1,0].TopLevelVarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("StatementListItem", 1, 0, Rhs(List(NonTerminal("Declaration", List(""), false)), None), "TopLevelVarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-toplevelvardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of Declaration Declaration0) {
  |    access HoistableDeclaration = (Declaration "HoistableDeclaration")
  |    1:access __x0__ = (HoistableDeclaration "BoundNames")
  |    1:return __x0__
  |  } else 1:{}
  |  2:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Declaration| is <emu-grammar>Declaration : HoistableDeclaration</emu-grammar> , then""",
    """          1. Return the BoundNames of |HoistableDeclaration|.""",
    """        1. Return a new empty List.""",
  )
}
