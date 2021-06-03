package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseBlock[0,0].CaseBlockEvaluation` extends Algo {
  val head = SyntaxDirectedHead("CaseBlock", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "CaseBlockEvaluation", List(Param("input", Normal)))
  val ids = List(
    "sec-runtime-semantics-caseblockevaluation",
    "sec-switch-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return undefined""".stripMargin)
  val code = scala.Array[String](
    """        1. Return NormalCompletion(*undefined*).""",
  )
}
