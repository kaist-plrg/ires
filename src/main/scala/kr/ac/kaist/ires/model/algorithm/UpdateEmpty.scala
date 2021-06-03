package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UpdateEmpty` extends Algo {
  val head = NormalHead("UpdateEmpty", List(Param("completionRecord", Normal), Param("value", Normal)))
  val ids = List(
    "sec-updateempty",
    "sec-completion-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= completionRecord.Value CONST_empty)) return completionRecord else 0:{}
  |  2:return (new Completion("Type" -> completionRecord.Type, "Value" -> value, "Target" -> completionRecord.Target))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: If _completionRecord_.[[Type]] is either ~return~ or ~throw~, then _completionRecord_.[[Value]] is not ~empty~.""",
    """          1. If _completionRecord_.[[Value]] is not ~empty~, return Completion(_completionRecord_).""",
    """          1. Return Completion { [[Type]]: _completionRecord_.[[Type]], [[Value]]: _value_, [[Target]]: _completionRecord_.[[Target]] }.""",
  )
}
