package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetValue` extends Algo {
  val head = NormalHead("GetValue", List(Param("V", Normal)))
  val ids = List(
    "sec-getvalue",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:[? V]
  |  1:if (! (is-instance-of V ReferenceRecord)) return V else 0:{}
  |  2:app __x0__ = (IsUnresolvableReference V)
  |  2:if (= __x0__ true) throw ReferenceError else 0:{}
  |  6:app __x1__ = (IsPropertyReference V)
  |  6:if (= __x1__ true) {
  |    4:app __x2__ = (ToObject V.Base)
  |    4:let baseObj = [! __x2__]
  |    5:app __x3__ = (GetThisValue V)
  |    5:app __x4__ = (baseObj.Get baseObj V.ReferencedName __x3__)
  |    5:return [? __x4__]
  |  } else {
  |    7:let base = V.Base
  |    8:assert (is-instance-of base EnvironmentRecord)
  |    9:app __x5__ = (base.GetBindingValue base V.ReferencedName V.Strict)
  |    9:return [? __x5__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. ReturnIfAbrupt(_V_).""",
    """          1. If _V_ is not a Reference Record, return _V_.""",
    """          1. If IsUnresolvableReference(_V_) is *true*, throw a *ReferenceError* exception.""",
    """          1. If IsPropertyReference(_V_) is *true*, then""",
    """            1. [id="step-getvalue-toobject"] Let _baseObj_ be ! ToObject(_V_.[[Base]]).""",
    """            1. Return ? _baseObj_.[[Get]](_V_.[[ReferencedName]], GetThisValue(_V_)).""",
    """          1. Else,""",
    """            1. Let _base_ be _V_.[[Base]].""",
    """            1. Assert: _base_ is an Environment Record.""",
    """            1. Return ? _base_.GetBindingValue(_V_.[[ReferencedName]], _V_.[[Strict]]) (see <emu-xref href="#sec-environment-records"></emu-xref>).""",
  )
}
