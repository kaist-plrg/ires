package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[3,0].ModuleRequests` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 3, 0, Rhs(List(Terminal("export"), NonTerminal("Declaration", List(""), false)), None), "ModuleRequests", List())
  val ids = List(
    "sec-static-semantics-modulerequests",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a new empty List.""",
  )
}
