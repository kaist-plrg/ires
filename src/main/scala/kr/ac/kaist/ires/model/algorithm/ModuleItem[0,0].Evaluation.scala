package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItem[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ModuleItem", 0, 0, Rhs(List(NonTerminal("ImportDeclaration", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-module-semantics-runtime-semantics-evaluation",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """          1. Return NormalCompletion(~empty~).""",
  )
}
