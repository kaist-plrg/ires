package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[2,0].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 2, 0, Rhs(List(Terminal("export"), NonTerminal("VariableStatement", List(""), false)), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a new empty List.""",
  )
}
