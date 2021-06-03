package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[0,0].ExportEntries` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 0, 0, Rhs(List(Terminal("export"), NonTerminal("ExportFromClause", List(""), false), NonTerminal("FromClause", List(""), false), Terminal(";")), None), "ExportEntries", List())
  val ids = List(
    "sec-static-semantics-exportentries",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FromClause "ModuleRequests")
  |  0:let module = __x0__[0i]
  |  1:access __x1__ = (ExportFromClause "ExportEntriesForModule" module)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _module_ be the sole element of ModuleRequests of |FromClause|.""",
    """          1. Return ExportEntriesForModule of |ExportFromClause| with argument _module_.""",
  )
}
