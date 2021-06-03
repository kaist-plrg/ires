package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[2,0].ExportEntries` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 2, 0, Rhs(List(Terminal("export"), NonTerminal("VariableStatement", List(""), false)), None), "ExportEntries", List())
  val ids = List(
    "sec-static-semantics-exportentries",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:let entries = (new [])
  |  1:access __x0__ = (VariableStatement "BoundNames")
  |  1:let names = __x0__
  |  2:let __x1__ = names
  |  2:let __x2__ = 0i
  |  2:while (< __x2__ __x1__.length) {
  |    let name = __x1__[__x2__]
  |    3:append (new ExportEntryRecord("ModuleRequest" -> null, "ImportName" -> null, "LocalName" -> name, "ExportName" -> name)) -> entries
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  4:return entries
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _entries_ be a new empty List.""",
    """          1. Let _names_ be the BoundNames of |VariableStatement|.""",
    """          1. For each element _name_ of _names_, do""",
    """            1. Append the ExportEntry Record { [[ModuleRequest]]: *null*, [[ImportName]]: *null*, [[LocalName]]: _name_, [[ExportName]]: _name_ } to _entries_.""",
    """          1. Return _entries_.""",
  )
}
