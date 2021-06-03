package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Block[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("Block", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "Evaluation", List())
  val ids = List(
    "sec-block-runtime-semantics-evaluation",
    "sec-block",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return CONST_empty""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(~empty~).""",
  )
}
