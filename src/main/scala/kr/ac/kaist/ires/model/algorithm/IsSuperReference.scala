package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsSuperReference` extends Algo {
  val head = NormalHead("IsSuperReference", List(Param("V", Normal)))
  val ids = List(
    "sec-issuperreference",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of V ReferenceRecord)
  |  1:if (! (= V.ThisValue CONST_empty)) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _V_ is a Reference Record.""",
    """          1. If _V_.[[ThisValue]] is not ~empty~, return *true*; otherwise return *false*.""",
  )
}
