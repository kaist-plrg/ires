package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.Get` extends Algo {
  val head = MethodHead("ProxyObject", "Get", Param("O", Normal), List(Param("P", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-get-p-receiver",
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
  |  5:app __x1__ = (GetMethod handler "get")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (target.Get target P Receiver)
  |    7:return [? __x2__]
  |  } else 0:{}
  |  8:app __x3__ = (Call trap handler (new [target, P, Receiver]))
  |  8:let trapResult = [? __x3__]
  |  9:app __x4__ = (target.GetOwnProperty target P)
  |  9:let targetDesc = [? __x4__]
  |  10:if (&& (! (= targetDesc undefined)) (= targetDesc.Configurable false)) {
  |    11:app __x5__ = (IsDataDescriptor targetDesc)
  |    11:if (&& (= __x5__ true) (= targetDesc.Writable false)) {
  |      12:app __x6__ = (SameValue trapResult targetDesc.Value)
  |      12:if (= __x6__ false) throw TypeError else 0:{}
  |    } else 0:{}
  |    13:app __x7__ = (IsAccessorDescriptor targetDesc)
  |    13:if (&& (= __x7__ true) (= targetDesc.Get undefined)) if (! (= trapResult undefined)) throw TypeError else 0:{} else 0:{}
  |  } else 0:{}
  |  15:return trapResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"get"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[Get]](_P_, _Receiver_).""",
    """        1. Let _trapResult_ be ? Call(_trap_, _handler_, « _target_, _P_, _Receiver_ »).""",
    """        1. Let _targetDesc_ be ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. If _targetDesc_ is not *undefined* and _targetDesc_.[[Configurable]] is *false*, then""",
    """          1. If IsDataDescriptor(_targetDesc_) is *true* and _targetDesc_.[[Writable]] is *false*, then""",
    """            1. If SameValue(_trapResult_, _targetDesc_.[[Value]]) is *false*, throw a *TypeError* exception.""",
    """          1. If IsAccessorDescriptor(_targetDesc_) is *true* and _targetDesc_.[[Get]] is *undefined*, then""",
    """            1. If _trapResult_ is not *undefined*, throw a *TypeError* exception.""",
    """        1. Return _trapResult_.""",
  )
}
