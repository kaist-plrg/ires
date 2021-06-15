package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportSpecifier[1,0].ImportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ImportSpecifier", 1, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false), Terminal("as"), NonTerminal("ImportedBinding", List(""), false)), None), "ImportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-importentriesformodule",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName "StringValue")
  |  0:let importName = __x0__
  |  1:access __x1__ = (ImportedBinding "StringValue")
  |  1:let localName = __x1__
  |  2:let entry = (new ImportEntryRecord("ModuleRequest" -> module, "ImportName" -> importName, "LocalName" -> localName))
  |  3:return (new [entry])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _importName_ be the StringValue of |IdentifierName|.""",
    """          1. Let _localName_ be the StringValue of |ImportedBinding|.""",
    """          1. Let _entry_ be the ImportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: _importName_, [[LocalName]]: _localName_ }.""",
    """          1. Return a List whose sole element is _entry_.""",
  )
}
