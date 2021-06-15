package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.SetPrototypeOf` extends Algo {
  val head = MethodHead("ProxyObject", "SetPrototypeOf", Param("O", Normal), List(Param("V", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-setprototypeof-v",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  1:let handler = O.ProxyHandler
  |  2:if (= handler null) throw TypeError else 0:{}
  |  3:assert (= (typeof handler) Object)
  |  4:let target = O.ProxyTarget
  |  5:app __x0__ = (GetMethod handler "setPrototypeOf")
  |  5:let trap = [? __x0__]
  |  6:if (= trap undefined) {
  |    7:app __x1__ = (target.SetPrototypeOf target V)
  |    7:return [? __x1__]
  |  } else 0:{}
  |  8:app __x2__ = (Call trap handler (new [target, V]))
  |  8:app __x3__ = (ToBoolean [? __x2__])
  |  8:let booleanTrapResult = [! __x3__]
  |  9:if (= booleanTrapResult false) return false else 0:{}
  |  10:app __x4__ = (IsExtensible target)
  |  10:let extensibleTarget = [? __x4__]
  |  11:if (= extensibleTarget true) return true else 0:{}
  |  12:app __x5__ = (target.GetPrototypeOf target)
  |  12:let targetProto = [? __x5__]
  |  13:app __x6__ = (SameValue V targetProto)
  |  13:if (= __x6__ false) throw TypeError else 0:{}
  |  14:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: Either Type(_V_) is Object or Type(_V_) is Null.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"setPrototypeOf"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[SetPrototypeOf]](_V_).""",
    """        1. Let _booleanTrapResult_ be ! ToBoolean(? Call(_trap_, _handler_, « _target_, _V_ »)).""",
    """        1. If _booleanTrapResult_ is *false*, return *false*.""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. If _extensibleTarget_ is *true*, return *true*.""",
    """        1. Let _targetProto_ be ? _target_.[[GetPrototypeOf]]().""",
    """        1. If SameValue(_V_, _targetProto_) is *false*, throw a *TypeError* exception.""",
    """        1. Return *true*.""",
  )
}
