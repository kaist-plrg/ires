package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportDeclaration[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ExportDeclaration", 1, 0, Rhs(List(Terminal("export"), NonTerminal("NamedExports", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-exports-runtime-semantics-evaluation",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """          1. Return NormalCompletion(~empty~).""",
  )
}
