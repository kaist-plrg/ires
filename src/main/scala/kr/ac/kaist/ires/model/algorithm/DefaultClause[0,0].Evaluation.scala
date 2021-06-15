package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DefaultClause[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("DefaultClause", 0, 0, Rhs(List(Terminal("default"), Terminal(":")), None), "Evaluation", List())
  val ids = List(
    "sec-switch-statement-runtime-semantics-evaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(~empty~).""",
  )
}
