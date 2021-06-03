package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[4,0].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 4, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("HoistableDeclaration", List(""), false)), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (HoistableDeclaration "DeclarationPart")
  |  0:return (new [__x0__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a List whose sole element is DeclarationPart of |HoistableDeclaration|.""",
  )
}
