package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[6,0].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 6, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("AssignmentExpression", List(""), false), Terminal(";")), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [this])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a List whose sole element is this |ExportDeclaration|.""",
  )
}
