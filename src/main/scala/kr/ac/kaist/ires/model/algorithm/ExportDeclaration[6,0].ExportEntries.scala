package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[6,0].ExportEntries` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 6, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("AssignmentExpression", List(""), false), Terminal(";")), None), "ExportEntries", List())
  val ids = List(
    "sec-static-semantics-exportentries",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:let entry = (new ExportEntryRecord("ModuleRequest" -> null, "ImportName" -> null, "LocalName" -> "*default*", "ExportName" -> "default"))
  |  1:return (new [entry])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _entry_ be the ExportEntry Record { [[ModuleRequest]]: *null*, [[ImportName]]: *null*, [[LocalName]]: *"\*default\*"*, [[ExportName]]: *"default"* }.""",
    """          1. Return a List whose sole element is _entry_.""",
  )
}
