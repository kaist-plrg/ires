package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.Set` extends Algo {
  val head = MethodHead("ProxyObject", "Set", Param("O", Normal), List(Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-set-p-v-receiver",
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
  |  5:app __x1__ = (GetMethod handler "set")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (target.Set target P V Receiver)
  |    7:return [? __x2__]
  |  } else 0:{}
  |  8:app __x3__ = (Call trap handler (new [target, P, V, Receiver]))
  |  8:app __x4__ = (ToBoolean [? __x3__])
  |  8:let booleanTrapResult = [! __x4__]
  |  9:if (= booleanTrapResult false) return false else 0:{}
  |  10:app __x5__ = (target.GetOwnProperty target P)
  |  10:let targetDesc = [? __x5__]
  |  11:if (&& (! (= targetDesc undefined)) (= targetDesc.Configurable false)) {
  |    12:app __x6__ = (IsDataDescriptor targetDesc)
  |    12:if (&& (= __x6__ true) (= targetDesc.Writable false)) {
  |      13:app __x7__ = (SameValue V targetDesc.Value)
  |      13:if (= __x7__ false) throw TypeError else 0:{}
  |    } else 0:{}
  |    14:app __x8__ = (IsAccessorDescriptor targetDesc)
  |    14:if (= __x8__ true) if (= targetDesc.Set undefined) throw TypeError else 0:{} else 0:{}
  |  } else 0:{}
  |  16:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"set"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[Set]](_P_, _V_, _Receiver_).""",
    """        1. Let _booleanTrapResult_ be ! ToBoolean(? Call(_trap_, _handler_, « _target_, _P_, _V_, _Receiver_ »)).""",
    """        1. If _booleanTrapResult_ is *false*, return *false*.""",
    """        1. Let _targetDesc_ be ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. If _targetDesc_ is not *undefined* and _targetDesc_.[[Configurable]] is *false*, then""",
    """          1. If IsDataDescriptor(_targetDesc_) is *true* and _targetDesc_.[[Writable]] is *false*, then""",
    """            1. If SameValue(_V_, _targetDesc_.[[Value]]) is *false*, throw a *TypeError* exception.""",
    """          1. If IsAccessorDescriptor(_targetDesc_) is *true*, then""",
    """            1. If _targetDesc_.[[Set]] is *undefined*, throw a *TypeError* exception.""",
    """        1. Return *true*.""",
  )
}
