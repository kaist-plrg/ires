package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrayExoticObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("ArrayExoticObject", "DefineOwnProperty", Param("A", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-array-exotic-objects-defineownproperty-p-desc",
    "sec-array-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  3:if (= P "length") {
  |    2:app __x1__ = (ArraySetLength A Desc)
  |    2:return [? __x1__]
  |  } else {
  |    app __x2__ = (IsArrayIndex P)
  |    if __x2__ {
  |      4:app __x3__ = (OrdinaryGetOwnProperty A "length")
  |      4:let oldLenDesc = __x3__
  |      5:app __x4__ = (IsDataDescriptor oldLenDesc)
  |      5:assert (= [! __x4__] true)
  |      6:assert (= oldLenDesc.Configurable false)
  |      7:let oldLen = oldLenDesc.Value
  |      9:app __x5__ = (ToUint32 P)
  |      9:let index = [! __x5__]
  |      10:if (&& (! (< index oldLen)) (= oldLenDesc.Writable false)) return false else 8:{}
  |      11:app __x6__ = (OrdinaryDefineOwnProperty A P Desc)
  |      11:let succeeded = [! __x6__]
  |      12:if (= succeeded false) return false else 8:{}
  |      13:if (! (< index oldLen)) {
  |        14:oldLenDesc.Value = (+ index 1i)
  |        15:app __x7__ = (OrdinaryDefineOwnProperty A "length" oldLenDesc)
  |        15:let succeeded = __x7__
  |        16:assert (= succeeded true)
  |      } else 8:{}
  |      17:return true
  |    } else 8:{}
  |  }
  |  18:app __x8__ = (OrdinaryDefineOwnProperty A P Desc)
  |  18:return __x8__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If _P_ is *"length"*, then""",
    """            1. Return ? ArraySetLength(_A_, _Desc_).""",
    """          1. Else if _P_ is an array index, then""",
    """            1. Let _oldLenDesc_ be OrdinaryGetOwnProperty(_A_, *"length"*).""",
    """            1. Assert: ! IsDataDescriptor(_oldLenDesc_) is *true*.""",
    """            1. Assert: _oldLenDesc_.[[Configurable]] is *false*.""",
    """            1. Let _oldLen_ be _oldLenDesc_.[[Value]].""",
    """            1. Assert: _oldLen_ is a non-negative integral Number.""",
    """            1. Let _index_ be ! ToUint32(_P_).""",
    """            1. If _index_ ≥ _oldLen_ and _oldLenDesc_.[[Writable]] is *false*, return *false*.""",
    """            1. Let _succeeded_ be ! OrdinaryDefineOwnProperty(_A_, _P_, _Desc_).""",
    """            1. If _succeeded_ is *false*, return *false*.""",
    """            1. If _index_ ≥ _oldLen_, then""",
    """              1. Set _oldLenDesc_.[[Value]] to _index_ + *1*<sub>𝔽</sub>.""",
    """              1. Let _succeeded_ be OrdinaryDefineOwnProperty(_A_, *"length"*, _oldLenDesc_).""",
    """              1. Assert: _succeeded_ is *true*.""",
    """            1. Return *true*.""",
    """          1. Return OrdinaryDefineOwnProperty(_A_, _P_, _Desc_).""",
  )
}
