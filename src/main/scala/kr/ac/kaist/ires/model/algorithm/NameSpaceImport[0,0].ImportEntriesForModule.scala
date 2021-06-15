package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NameSpaceImport[0,0].ImportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("NameSpaceImport", 0, 0, Rhs(List(Terminal("*"), Terminal("as"), NonTerminal("ImportedBinding", List(""), false)), None), "ImportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-importentriesformodule",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportedBinding "StringValue")
  |  0:let localName = __x0__
  |  1:let entry = (new ImportEntryRecord("ModuleRequest" -> module, "ImportName" -> "*", "LocalName" -> localName))
  |  2:return (new [entry])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _localName_ be the StringValue of |ImportedBinding|.""",
    """          1. Let _entry_ be the ImportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: *"\*"*, [[LocalName]]: _localName_ }.""",
    """          1. Return a List whose sole element is _entry_.""",
  )
}
