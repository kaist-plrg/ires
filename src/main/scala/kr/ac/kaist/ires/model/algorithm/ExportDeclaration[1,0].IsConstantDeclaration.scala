package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[1,0].IsConstantDeclaration` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 1, 0, Rhs(List(Terminal("export"), NonTerminal("NamedExports", List(""), false), Terminal(";")), None), "IsConstantDeclaration", List())
  val ids = List(
    "sec-static-semantics-isconstantdeclaration",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
