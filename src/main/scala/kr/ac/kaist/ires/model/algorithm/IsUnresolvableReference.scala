package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsUnresolvableReference` extends Algo {
  val head = NormalHead("IsUnresolvableReference", List(Param("V", Normal)))
  val ids = List(
    "sec-isunresolvablereference",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of V ReferenceRecord)
  |  1:if (= V.Base CONST_unresolvable) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _V_ is a Reference Record.""",
    """          1. If _V_.[[Base]] is ~unresolvable~, return *true*; otherwise return *false*.""",
  )
}
