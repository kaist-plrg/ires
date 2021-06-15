package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportFromClause[1,0].ExportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ExportFromClause", 1, 0, Rhs(List(Terminal("*"), Terminal("as"), NonTerminal("IdentifierName", List(""), false)), None), "ExportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-exportentriesformodule",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName "StringValue")
  |  0:let exportName = __x0__
  |  1:let entry = (new ExportEntryRecord("ModuleRequest" -> module, "ImportName" -> "*", "LocalName" -> null, "ExportName" -> exportName))
  |  2:return (new [entry])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _exportName_ be the StringValue of |IdentifierName|.""",
    """          1. Let _entry_ be the ExportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: *"\*"*, [[LocalName]]: *null*, [[ExportName]]: _exportName_ }.""",
    """          1. Return a List whose sole element is _entry_.""",
  )
}
