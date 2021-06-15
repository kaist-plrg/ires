package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArraySetLength` extends Algo {
  val head = NormalHead("ArraySetLength", List(Param("A", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-arraysetlength",
    "sec-array-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (= Desc.Value absent) {
  |    1:app __x0__ = (OrdinaryDefineOwnProperty A "length" Desc)
  |    1:return __x0__
  |  } else 16:{}
  |  2:let newLenDesc = (copy-obj Desc)
  |  3:app __x1__ = (ToUint32 Desc.Value)
  |  3:let newLen = [? __x1__]
  |  4:app __x2__ = (ToNumber Desc.Value)
  |  4:let numberLen = [? __x2__]
  |  5:if (! (= newLen numberLen)) throw RangeError else 16:{}
  |  6:newLenDesc.Value = newLen
  |  7:app __x3__ = (OrdinaryGetOwnProperty A "length")
  |  7:let oldLenDesc = __x3__
  |  8:app __x4__ = (IsDataDescriptor oldLenDesc)
  |  8:assert (= [! __x4__] true)
  |  9:assert (= oldLenDesc.Configurable false)
  |  10:let oldLen = oldLenDesc.Value
  |  11:if (! (< newLen oldLen)) {
  |    12:app __x5__ = (OrdinaryDefineOwnProperty A "length" newLenDesc)
  |    12:return __x5__
  |  } else 16:{}
  |  13:if (= oldLenDesc.Writable false) return false else 16:{}
  |  15:if (|| (= newLenDesc.Writable absent) (= newLenDesc.Writable true)) let newWritable = true else {
  |    17:let newWritable = false
  |    18:newLenDesc.Writable = true
  |  }
  |  19:app __x6__ = (OrdinaryDefineOwnProperty A "length" newLenDesc)
  |  19:let succeeded = [! __x6__]
  |  20:if (= succeeded false) return false else 16:{}
  |  28:if (= newWritable false) {
  |    29:app __x7__ = (OrdinaryDefineOwnProperty A "length" (new PropertyDescriptor("Writable" -> false)))
  |    29:let succeeded = [! __x7__]
  |    30:assert (= succeeded true)
  |  } else 16:{}
  |  31:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _Desc_.[[Value]] is absent, then""",
    """            1. Return OrdinaryDefineOwnProperty(_A_, *"length"*, _Desc_).""",
    """          1. Let _newLenDesc_ be a copy of _Desc_.""",
    """          1. [id="step-arraysetlength-newlen"] Let _newLen_ be ? ToUint32(_Desc_.[[Value]]).""",
    """          1. [id="step-arraysetlength-numberlen"] Let _numberLen_ be ? ToNumber(_Desc_.[[Value]]).""",
    """          1. If _newLen_ is not the same value as _numberLen_, throw a *RangeError* exception.""",
    """          1. Set _newLenDesc_.[[Value]] to _newLen_.""",
    """          1. Let _oldLenDesc_ be OrdinaryGetOwnProperty(_A_, *"length"*).""",
    """          1. Assert: ! IsDataDescriptor(_oldLenDesc_) is *true*.""",
    """          1. Assert: _oldLenDesc_.[[Configurable]] is *false*.""",
    """          1. Let _oldLen_ be _oldLenDesc_.[[Value]].""",
    """          1. If _newLen_ ≥ _oldLen_, then""",
    """            1. Return OrdinaryDefineOwnProperty(_A_, *"length"*, _newLenDesc_).""",
    """          1. If _oldLenDesc_.[[Writable]] is *false*, return *false*.""",
    """          1. If _newLenDesc_.[[Writable]] is absent or has the value *true*, let _newWritable_ be *true*.""",
    """          1. Else,""",
    """            1. NOTE: Setting the [[Writable]] attribute to *false* is deferred in case any elements cannot be deleted.""",
    """            1. Let _newWritable_ be *false*.""",
    """            1. Set _newLenDesc_.[[Writable]] to *true*.""",
    """          1. Let _succeeded_ be ! OrdinaryDefineOwnProperty(_A_, *"length"*, _newLenDesc_).""",
    """          1. If _succeeded_ is *false*, return *false*.""",
    """          1. For each own property key _P_ of _A_ that is an array index, whose numeric value is greater than or equal to _newLen_, in descending numeric index order, do""",
    """            1. Let _deleteSucceeded_ be ! _A_.[[Delete]](_P_).""",
    """            1. If _deleteSucceeded_ is *false*, then""",
    """              1. Set _newLenDesc_.[[Value]] to ! ToUint32(_P_) + *1*<sub>𝔽</sub>.""",
    """              1. If _newWritable_ is *false*, set _newLenDesc_.[[Writable]] to *false*.""",
    """              1. Perform ! OrdinaryDefineOwnProperty(_A_, *"length"*, _newLenDesc_).""",
    """              1. Return *false*.""",
    """          1. If _newWritable_ is *false*, then""",
    """            1. Let _succeeded_ be ! OrdinaryDefineOwnProperty(_A_, *"length"*, PropertyDescriptor { [[Writable]]: *false* }).""",
    """            1. Assert: _succeeded_ is *true*.""",
    """          1. Return *true*.""",
  )
}
