package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[2,0].ExportedBindings` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 2, 0, Rhs(List(Terminal("export"), NonTerminal("VariableStatement", List(""), false)), None), "ExportedBindings", List())
  val ids = List(
    "sec-static-semantics-exportedbindings",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (VariableStatement "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the BoundNames of |VariableStatement|.""",
  )
}
