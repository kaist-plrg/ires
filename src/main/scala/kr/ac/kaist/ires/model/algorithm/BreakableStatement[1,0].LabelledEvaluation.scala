package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakableStatement[1,0].LabelledEvaluation` extends Algo {
  val head = SyntaxDirectedHead("BreakableStatement", 1, 0, Rhs(List(NonTerminal("SwitchStatement", List(""), false)), None), "LabelledEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-labelledevaluation",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (SwitchStatement "Evaluation")
  |  0:let stmtResult = __x0__
  |  1:if (= stmtResult.Type CONST_break) if (= stmtResult.Target CONST_empty) if (= stmtResult.Value CONST_empty) {
  |    app __x1__ = (NormalCompletion undefined)
  |    stmtResult = __x1__
  |  } else {
  |    app __x2__ = (NormalCompletion stmtResult.Value)
  |    stmtResult = __x2__
  |  } else 0:{} else 0:{}
  |  5:return stmtResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _stmtResult_ be the result of evaluating |SwitchStatement|.""",
    """        1. If _stmtResult_.[[Type]] is ~break~, then""",
    """          1. If _stmtResult_.[[Target]] is ~empty~, then""",
    """            1. If _stmtResult_.[[Value]] is ~empty~, set _stmtResult_ to NormalCompletion(*undefined*).""",
    """            1. Else, set _stmtResult_ to NormalCompletion(_stmtResult_.[[Value]]).""",
    """        1. Return Completion(_stmtResult_).""",
  )
}
