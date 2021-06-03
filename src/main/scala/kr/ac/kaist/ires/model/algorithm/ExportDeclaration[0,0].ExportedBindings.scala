package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[0,0].ExportedBindings` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 0, 0, Rhs(List(Terminal("export"), NonTerminal("ExportFromClause", List(""), false), NonTerminal("FromClause", List(""), false), Terminal(";")), None), "ExportedBindings", List())
  val ids = List(
    "sec-static-semantics-exportedbindings",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a new empty List.""",
  )
}
