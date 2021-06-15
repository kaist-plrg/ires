package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("ProxyObject", "DefineOwnProperty", Param("O", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-defineownproperty-p-desc",
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
  |  5:app __x1__ = (GetMethod handler "defineProperty")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (target.DefineOwnProperty target P Desc)
  |    7:return [? __x2__]
  |  } else 0:{}
  |  8:app __x3__ = (FromPropertyDescriptor Desc)
  |  8:let descObj = __x3__
  |  9:app __x4__ = (Call trap handler (new [target, P, descObj]))
  |  9:app __x5__ = (ToBoolean [? __x4__])
  |  9:let booleanTrapResult = [! __x5__]
  |  10:if (= booleanTrapResult false) return false else 0:{}
  |  11:app __x6__ = (target.GetOwnProperty target P)
  |  11:let targetDesc = [? __x6__]
  |  12:app __x7__ = (IsExtensible target)
  |  12:let extensibleTarget = [? __x7__]
  |  15:if (&& (! (= Desc.Configurable absent)) (= Desc.Configurable false)) let settingConfigFalse = true else let settingConfigFalse = false
  |  19:if (= targetDesc undefined) {
  |    17:if (= extensibleTarget false) throw TypeError else 0:{}
  |    18:if (= settingConfigFalse true) throw TypeError else 0:{}
  |  } else {
  |    20:app __x8__ = (IsCompatiblePropertyDescriptor extensibleTarget Desc targetDesc)
  |    20:if (= __x8__ false) throw TypeError else 0:{}
  |    21:if (&& (= settingConfigFalse true) (= targetDesc.Configurable true)) throw TypeError else 0:{}
  |    22:app __x9__ = (IsDataDescriptor targetDesc)
  |    22:if (&& (&& (= __x9__ true) (= targetDesc.Configurable false)) (= targetDesc.Writable true)) if (&& (! (= Desc.Writable absent)) (= Desc.Writable false)) throw TypeError else 0:{} else 0:{}
  |  }
  |  24:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"defineProperty"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[DefineOwnProperty]](_P_, _Desc_).""",
    """        1. Let _descObj_ be FromPropertyDescriptor(_Desc_).""",
    """        1. Let _booleanTrapResult_ be ! ToBoolean(? Call(_trap_, _handler_, « _target_, _P_, _descObj_ »)).""",
    """        1. If _booleanTrapResult_ is *false*, return *false*.""",
    """        1. Let _targetDesc_ be ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. If _Desc_ has a [[Configurable]] field and if _Desc_.[[Configurable]] is *false*, then""",
    """          1. Let _settingConfigFalse_ be *true*.""",
    """        1. Else, let _settingConfigFalse_ be *false*.""",
    """        1. If _targetDesc_ is *undefined*, then""",
    """          1. If _extensibleTarget_ is *false*, throw a *TypeError* exception.""",
    """          1. If _settingConfigFalse_ is *true*, throw a *TypeError* exception.""",
    """        1. Else,""",
    """          1. If IsCompatiblePropertyDescriptor(_extensibleTarget_, _Desc_, _targetDesc_) is *false*, throw a *TypeError* exception.""",
    """          1. If _settingConfigFalse_ is *true* and _targetDesc_.[[Configurable]] is *true*, throw a *TypeError* exception.""",
    """          1. If IsDataDescriptor(_targetDesc_) is *true*, _targetDesc_.[[Configurable]] is *false*, and _targetDesc_.[[Writable]] is *true*, then""",
    """            1. If _Desc_ has a [[Writable]] field and _Desc_.[[Writable]] is *false*, throw a *TypeError* exception.""",
    """        1. Return *true*.""",
  )
}
