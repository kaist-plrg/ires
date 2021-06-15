package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinarySetWithOwnDescriptor` extends Algo {
  val head = NormalHead("OrdinarySetWithOwnDescriptor", List(Param("O", Normal), Param("P", Normal), Param("V", Normal), Param("Receiver", Normal), Param("ownDesc", Normal)))
  val ids = List(
    "sec-ordinarysetwithowndescriptor",
    "sec-ordinary-object-internal-methods-and-internal-slots-set-p-v-receiver",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= ownDesc undefined) {
  |    2:app __x1__ = (O.GetPrototypeOf O)
  |    2:let parent = [? __x1__]
  |    5:if (! (= parent null)) {
  |      4:app __x2__ = (parent.Set parent P V Receiver)
  |      4:return [? __x2__]
  |    } else ownDesc = (new PropertyDescriptor("Value" -> undefined, "Writable" -> true, "Enumerable" -> true, "Configurable" -> true))
  |  } else 17:{}
  |  7:app __x3__ = (IsDataDescriptor ownDesc)
  |  7:if (= __x3__ true) {
  |    8:if (= ownDesc.Writable false) return false else 17:{}
  |    9:if (! (= (typeof Receiver) Object)) return false else 17:{}
  |    10:app __x4__ = (Receiver.GetOwnProperty Receiver P)
  |    10:let existingDescriptor = [? __x4__]
  |    16:if (! (= existingDescriptor undefined)) {
  |      12:app __x5__ = (IsAccessorDescriptor existingDescriptor)
  |      12:if (= __x5__ true) return false else 17:{}
  |      13:if (= existingDescriptor.Writable false) return false else 17:{}
  |      14:let valueDesc = (new PropertyDescriptor("Value" -> V))
  |      15:app __x6__ = (Receiver.DefineOwnProperty Receiver P valueDesc)
  |      15:return [? __x6__]
  |    } else {
  |      18:app __x7__ = (CreateDataProperty Receiver P V)
  |      18:return [? __x7__]
  |    }
  |  } else 17:{}
  |  19:app __x8__ = (IsAccessorDescriptor ownDesc)
  |  19:assert (= __x8__ true)
  |  20:let setter = ownDesc.Set
  |  21:if (= setter undefined) return false else 17:{}
  |  22:app __x9__ = (Call setter Receiver (new [V]))
  |  22:[? __x9__]
  |  23:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If _ownDesc_ is *undefined*, then""",
    """            1. Let _parent_ be ? _O_.[[GetPrototypeOf]]().""",
    """            1. If _parent_ is not *null*, then""",
    """              1. Return ? _parent_.[[Set]](_P_, _V_, _Receiver_).""",
    """            1. Else,""",
    """              1. Set _ownDesc_ to the PropertyDescriptor { [[Value]]: *undefined*, [[Writable]]: *true*, [[Enumerable]]: *true*, [[Configurable]]: *true* }.""",
    """          1. If IsDataDescriptor(_ownDesc_) is *true*, then""",
    """            1. If _ownDesc_.[[Writable]] is *false*, return *false*.""",
    """            1. If Type(_Receiver_) is not Object, return *false*.""",
    """            1. Let _existingDescriptor_ be ? _Receiver_.[[GetOwnProperty]](_P_).""",
    """            1. If _existingDescriptor_ is not *undefined*, then""",
    """              1. If IsAccessorDescriptor(_existingDescriptor_) is *true*, return *false*.""",
    """              1. If _existingDescriptor_.[[Writable]] is *false*, return *false*.""",
    """              1. Let _valueDesc_ be the PropertyDescriptor { [[Value]]: _V_ }.""",
    """              1. Return ? _Receiver_.[[DefineOwnProperty]](_P_, _valueDesc_).""",
    """            1. Else,""",
    """              1. Assert: _Receiver_ does not currently have a property _P_.""",
    """              1. Return ? CreateDataProperty(_Receiver_, _P_, _V_).""",
    """          1. Assert: IsAccessorDescriptor(_ownDesc_) is *true*.""",
    """          1. Let _setter_ be _ownDesc_.[[Set]].""",
    """          1. If _setter_ is *undefined*, return *false*.""",
    """          1. Perform ? Call(_setter_, _Receiver_, « _V_ »).""",
    """          1. Return *true*.""",
  )
}
