package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeReferencedBinding` extends Algo {
  val head = NormalHead("InitializeReferencedBinding", List(Param("V", Normal), Param("W", Normal)))
  val ids = List(
    "sec-initializereferencedbinding",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:[? V]
  |  1:[? W]
  |  2:assert (is-instance-of V ReferenceRecord)
  |  3:app __x0__ = (IsUnresolvableReference V)
  |  3:assert (= __x0__ false)
  |  4:let base = V.Base
  |  5:assert (is-instance-of base EnvironmentRecord)
  |  6:app __x1__ = (base.InitializeBinding base V.ReferencedName W)
  |  6:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. ReturnIfAbrupt(_V_).""",
    """          1. ReturnIfAbrupt(_W_).""",
    """          1. Assert: _V_ is a Reference Record.""",
    """          1. Assert: IsUnresolvableReference(_V_) is *false*.""",
    """          1. Let _base_ be _V_.[[Base]].""",
    """          1. Assert: _base_ is an Environment Record.""",
    """          1. Return _base_.InitializeBinding(_V_.[[ReferencedName]], _W_).""",
  )
}
