package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::VariableDeclaration[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("VariableDeclaration", 0, 0, Rhs(List(NonTerminal("BindingIdentifier", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-variable-statement-runtime-semantics-evaluation",
    "sec-variable-statement",
    "sec-declarations-and-the-variable-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """          1. Return NormalCompletion(~empty~).""",
  )
}
