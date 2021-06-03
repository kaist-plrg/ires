package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsPropertyReference` extends Algo {
  val head = NormalHead("IsPropertyReference", List(Param("V", Normal)))
  val ids = List(
    "sec-ispropertyreference",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of V ReferenceRecord)
  |  1:if (= V.Base CONST_unresolvable) return false else 0:{}
  |  2:if (|| (|| (|| (|| (|| (= (typeof V.Base) Boolean) (= (typeof V.Base) String)) (= (typeof V.Base) Symbol)) (= (typeof V.Base) BigInt)) (= (typeof V.Base) Number)) (= (typeof V.Base) Object)) return true else return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _V_ is a Reference Record.""",
    """          1. If _V_.[[Base]] is ~unresolvable~, return *false*.""",
    """          1. If Type(_V_.[[Base]]) is Boolean, String, Symbol, BigInt, Number, or Object, return *true*; otherwise return *false*.""",
  )
}
