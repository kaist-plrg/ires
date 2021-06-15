package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 4, 0, Rhs(List(Terminal("export"), Terminal("default"), NonTerminal("HoistableDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-exports-runtime-semantics-evaluation",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (HoistableDeclaration "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of evaluating |HoistableDeclaration|.""",
  )
}
