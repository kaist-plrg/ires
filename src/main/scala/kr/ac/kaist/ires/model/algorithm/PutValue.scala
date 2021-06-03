package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PutValue` extends Algo {
  val head = NormalHead("PutValue", List(Param("V", Normal), Param("W", Normal)))
  val ids = List(
    "sec-putvalue",
    "sec-reference-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:[? V]
  |  1:[? W]
  |  2:if (! (is-instance-of V ReferenceRecord)) throw ReferenceError else 0:{}
  |  3:app __x0__ = (IsUnresolvableReference V)
  |  3:if (= __x0__ true) {
  |    4:if (= V.Strict true) throw ReferenceError else 0:{}
  |    5:app __x1__ = (GetGlobalObject)
  |    5:let globalObj = __x1__
  |    6:app __x2__ = (Set globalObj V.ReferencedName W false)
  |    6:return [? __x2__]
  |  } else 0:{}
  |  12:app __x3__ = (IsPropertyReference V)
  |  12:if (= __x3__ true) {
  |    8:app __x4__ = (ToObject V.Base)
  |    8:let baseObj = [! __x4__]
  |    9:app __x5__ = (GetThisValue V)
  |    9:app __x6__ = (baseObj.Set baseObj V.ReferencedName W __x5__)
  |    9:let succeeded = [? __x6__]
  |    10:if (&& (= succeeded false) (= V.Strict true)) throw TypeError else 0:{}
  |    11:return undefined
  |  } else {
  |    13:let base = V.Base
  |    14:assert (is-instance-of base EnvironmentRecord)
  |    15:app __x7__ = (base.SetMutableBinding base V.ReferencedName W V.Strict)
  |    15:return [? __x7__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. ReturnIfAbrupt(_V_).""",
    """          1. ReturnIfAbrupt(_W_).""",
    """          1. If _V_ is not a Reference Record, throw a *ReferenceError* exception.""",
    """          1. If IsUnresolvableReference(_V_) is *true*, then""",
    """            1. If _V_.[[Strict]] is *true*, throw a *ReferenceError* exception.""",
    """            1. Let _globalObj_ be GetGlobalObject().""",
    """            1. Return ? Set(_globalObj_, _V_.[[ReferencedName]], _W_, *false*).""",
    """          1. If IsPropertyReference(_V_) is *true*, then""",
    """            1. [id="step-putvalue-toobject"] Let _baseObj_ be ! ToObject(_V_.[[Base]]).""",
    """            1. Let _succeeded_ be ? _baseObj_.[[Set]](_V_.[[ReferencedName]], _W_, GetThisValue(_V_)).""",
    """            1. If _succeeded_ is *false* and _V_.[[Strict]] is *true*, throw a *TypeError* exception.""",
    """            1. Return.""",
    """          1. Else,""",
    """            1. Let _base_ be _V_.[[Base]].""",
    """            1. Assert: _base_ is an Environment Record.""",
    """            1. Return ? _base_.SetMutableBinding(_V_.[[ReferencedName]], _W_, _V_.[[Strict]]) (see <emu-xref href="#sec-environment-records"></emu-xref>).""",
  )
}
