package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EmptyStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("EmptyStatement", 0, 0, Rhs(List(Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-empty-statement-runtime-semantics-evaluation",
    "sec-empty-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(~empty~).""",
  )
}
