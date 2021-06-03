package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementListItem[1,0].TopLevelLexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("StatementListItem", 1, 0, Rhs(List(NonTerminal("Declaration", List(""), false)), None), "TopLevelLexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevellexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of Declaration Declaration0) {
  |    access HoistableDeclaration = (Declaration "HoistableDeclaration")
  |    1:return (new [])
  |  } else 1:{}
  |  2:return (new [Declaration])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Declaration| is <emu-grammar>Declaration : HoistableDeclaration</emu-grammar> , then""",
    """          1. Return « ».""",
    """        1. Return a List whose sole element is |Declaration|.""",
  )
}
