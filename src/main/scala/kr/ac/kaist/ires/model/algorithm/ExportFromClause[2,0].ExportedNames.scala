package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportFromClause[2,0].ExportedNames` extends Algo {
  val head = SyntaxDirectedHead("ExportFromClause", 2, 0, Rhs(List(NonTerminal("NamedExports", List(""), false)), None), "ExportedNames", List())
  val ids = List(
    "sec-static-semantics-exportednames",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (NamedExports "ExportedNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the ExportedNames of |NamedExports|.""",
  )
}
