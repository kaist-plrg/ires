package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[5,0].ExportEntries` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 5, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("ClassDeclaration", List(""), false)), None), "ExportEntries", List())
  val ids = List(
    "sec-static-semantics-exportentries",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassDeclaration "BoundNames")
  |  0:let names = __x0__
  |  1:let localName = names[0i]
  |  2:return (new [(new ExportEntryRecord("ModuleRequest" -> null, "ImportName" -> null, "LocalName" -> localName, "ExportName" -> "default"))])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _names_ be BoundNames of |ClassDeclaration|.""",
    """          1. Let _localName_ be the sole element of _names_.""",
    """          1. Return a List whose sole element is the ExportEntry Record { [[ModuleRequest]]: *null*, [[ImportName]]: *null*, [[LocalName]]: _localName_, [[ExportName]]: *"default"* }.""",
  )
}
