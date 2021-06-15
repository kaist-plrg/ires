package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[4,0].ExportedNames` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 4, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("HoistableDeclaration", List(""), false)), None), "ExportedNames", List())
  val ids = List(
    "sec-static-semantics-exportednames",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new ["default"])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return « *"default"* ».""",
  )
}
