package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportSpecifier[1,0].ExportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ExportSpecifier", 1, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false), Terminal("as"), NonTerminal("IdentifierName", List(""), false)), None), "ExportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-exportentriesformodule",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName0 "StringValue")
  |  0:let sourceName = __x0__
  |  1:access __x1__ = (IdentifierName1 "StringValue")
  |  1:let exportName = __x1__
  |  5:if (= module null) {
  |    3:let localName = sourceName
  |    4:let importName = null
  |  } else {
  |    6:let localName = null
  |    7:let importName = sourceName
  |  }
  |  8:return (new [(new ExportEntryRecord("ModuleRequest" -> module, "ImportName" -> importName, "LocalName" -> localName, "ExportName" -> exportName))])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _sourceName_ be the StringValue of the first |IdentifierName|.""",
    """          1. Let _exportName_ be the StringValue of the second |IdentifierName|.""",
    """          1. If _module_ is *null*, then""",
    """            1. Let _localName_ be _sourceName_.""",
    """            1. Let _importName_ be *null*.""",
    """          1. Else,""",
    """            1. Let _localName_ be *null*.""",
    """            1. Let _importName_ be _sourceName_.""",
    """          1. Return a List whose sole element is the ExportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: _importName_, [[LocalName]]: _localName_, [[ExportName]]: _exportName_ }.""",
  )
}
