package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.GetOwnProperty` extends Algo {
  val head = MethodHead("ProxyObject", "GetOwnProperty", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-getownproperty-p",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:let handler = O.ProxyHandler
  |  2:if (= handler null) throw TypeError else 0:{}
  |  3:assert (= (typeof handler) Object)
  |  4:let target = O.ProxyTarget
  |  5:app __x1__ = (GetMethod handler "getOwnPropertyDescriptor")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (target.GetOwnProperty target P)
  |    7:return [? __x2__]
  |  } else 0:{}
  |  8:app __x3__ = (Call trap handler (new [target, P]))
  |  8:let trapResultObj = [? __x3__]
  |  9:if (! (|| (= (typeof trapResultObj) Object) (= (typeof trapResultObj) Undefined))) throw TypeError else 0:{}
  |  10:app __x4__ = (target.GetOwnProperty target P)
  |  10:let targetDesc = [? __x4__]
  |  11:if (= trapResultObj undefined) {
  |    12:if (= targetDesc undefined) return undefined else 0:{}
  |    13:if (= targetDesc.Configurable false) throw TypeError else 0:{}
  |    14:app __x5__ = (IsExtensible target)
  |    14:let extensibleTarget = [? __x5__]
  |    15:if (= extensibleTarget false) throw TypeError else 0:{}
  |    16:return undefined
  |  } else 0:{}
  |  17:app __x6__ = (IsExtensible target)
  |  17:let extensibleTarget = [? __x6__]
  |  18:app __x7__ = (ToPropertyDescriptor trapResultObj)
  |  18:let resultDesc = [? __x7__]
  |  19:app __x8__ = (CompletePropertyDescriptor resultDesc)
  |  19:__x8__
  |  20:app __x9__ = (IsCompatiblePropertyDescriptor extensibleTarget resultDesc targetDesc)
  |  20:let valid = __x9__
  |  21:if (= valid false) throw TypeError else 0:{}
  |  22:if (= resultDesc.Configurable false) {
  |    23:if (|| (= targetDesc undefined) (= targetDesc.Configurable true)) throw TypeError else 0:{}
  |    25:if (&& (! (= resultDesc.Writable absent)) (= resultDesc.Writable false)) if (= targetDesc.Writable true) throw TypeError else 0:{} else 0:{}
  |  } else 0:{}
  |  27:return resultDesc
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"getOwnPropertyDescriptor"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. Let _trapResultObj_ be ? Call(_trap_, _handler_, « _target_, _P_ »).""",
    """        1. If Type(_trapResultObj_) is neither Object nor Undefined, throw a *TypeError* exception.""",
    """        1. Let _targetDesc_ be ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. If _trapResultObj_ is *undefined*, then""",
    """          1. If _targetDesc_ is *undefined*, return *undefined*.""",
    """          1. If _targetDesc_.[[Configurable]] is *false*, throw a *TypeError* exception.""",
    """          1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """          1. If _extensibleTarget_ is *false*, throw a *TypeError* exception.""",
    """          1. Return *undefined*.""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. Let _resultDesc_ be ? ToPropertyDescriptor(_trapResultObj_).""",
    """        1. Call CompletePropertyDescriptor(_resultDesc_).""",
    """        1. Let _valid_ be IsCompatiblePropertyDescriptor(_extensibleTarget_, _resultDesc_, _targetDesc_).""",
    """        1. If _valid_ is *false*, throw a *TypeError* exception.""",
    """        1. If _resultDesc_.[[Configurable]] is *false*, then""",
    """          1. If _targetDesc_ is *undefined* or _targetDesc_.[[Configurable]] is *true*, then""",
    """            1. Throw a *TypeError* exception.""",
    """          1. If _resultDesc_ has a [[Writable]] field and _resultDesc_.[[Writable]] is *false*, then""",
    """            1. If _targetDesc_.[[Writable]] is *true*, throw a *TypeError* exception.""",
    """        1. Return _resultDesc_.""",
  )
}
