package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportSpecifier[0,0].ImportEntriesForModule` extends Algo {
  val head = SyntaxDirectedHead("ImportSpecifier", 0, 0, Rhs(List(NonTerminal("ImportedBinding", List(""), false)), None), "ImportEntriesForModule", List(Param("module", Normal)))
  val ids = List(
    "sec-static-semantics-importentriesformodule",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportedBinding "BoundNames")
  |  0:let localName = __x0__[0i]
  |  1:let entry = (new ImportEntryRecord("ModuleRequest" -> module, "ImportName" -> localName, "LocalName" -> localName))
  |  2:return (new [entry])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _localName_ be the sole element of BoundNames of |ImportedBinding|.""",
    """          1. Let _entry_ be the ImportEntry Record { [[ModuleRequest]]: _module_, [[ImportName]]: _localName_, [[LocalName]]: _localName_ }.""",
    """          1. Return a List whose sole element is _entry_.""",
  )
}
