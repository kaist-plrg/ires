package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportSpecifier[0,0].ExportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ExportSpecifier", 0, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false)), None), "ExportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-exportentriesformodule",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName "StringValue")
  |  0:let sourceName = __x0__
  |  4:if (= module null) {
  |    2:let localName = sourceName
  |    3:let importName = null
  |  } else {
  |    5:let localName = null
  |    6:let importName = sourceName
  |  }
  |  7:return (new [(new ExportEntryRecord("ModuleRequest" -> module, "ImportName" -> importName, "LocalName" -> localName, "ExportName" -> sourceName))])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _sourceName_ be the StringValue of |IdentifierName|.""",
    """          1. If _module_ is *null*, then""",
    """            1. Let _localName_ be _sourceName_.""",
    """            1. Let _importName_ be *null*.""",
    """          1. Else,""",
    """            1. Let _localName_ be *null*.""",
    """            1. Let _importName_ be _sourceName_.""",
    """          1. Return a List whose sole element is the ExportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: _importName_, [[LocalName]]: _localName_, [[ExportName]]: _sourceName_ }.""",
  )
}
