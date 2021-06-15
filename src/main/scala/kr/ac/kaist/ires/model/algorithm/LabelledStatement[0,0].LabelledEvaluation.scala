package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledStatement[0,0].LabelledEvaluation` extends Algo {
  val head = SyntaxDirectedHead("LabelledStatement", 0, 0, Rhs(List(NonTerminal("LabelIdentifier", List(""), false), Terminal(":"), NonTerminal("LabelledItem", List(""), false)), None), "LabelledEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-labelledevaluation",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:let label = __x0__
  |  1:append label -> labelSet
  |  2:access __x1__ = (LabelledItem "LabelledEvaluation" labelSet)
  |  2:let stmtResult = __x1__
  |  3:let __x2__ = true
  |  3:__x2__ = (= stmtResult.Type CONST_break)
  |  3:if __x2__ {
  |    app __x3__ = (SameValue stmtResult.Target label)
  |    __x2__ = (= __x3__ true)
  |  } else 0:{}
  |  3:if __x2__ {
  |    4:app __x4__ = (NormalCompletion stmtResult.Value)
  |    4:stmtResult = __x4__
  |  } else 0:{}
  |  5:return stmtResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _label_ be the StringValue of |LabelIdentifier|.""",
    """        1. Append _label_ as an element of _labelSet_.""",
    """        1. Let _stmtResult_ be LabelledEvaluation of |LabelledItem| with argument _labelSet_.""",
    """        1. If _stmtResult_.[[Type]] is ~break~ and SameValue(_stmtResult_.[[Target]], _label_) is *true*, then""",
    """          1. Set _stmtResult_ to NormalCompletion(_stmtResult_.[[Value]]).""",
    """        1. Return Completion(_stmtResult_).""",
  )
}
