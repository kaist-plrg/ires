package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.GetPrototypeOf` extends Algo {
  val head = MethodHead("ProxyObject", "GetPrototypeOf", Param("O", Normal), List())
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-getprototypeof",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let handler = O.ProxyHandler
  |  1:if (= handler null) throw TypeError else 0:{}
  |  2:assert (= (typeof handler) Object)
  |  3:let target = O.ProxyTarget
  |  4:app __x0__ = (GetMethod handler "getPrototypeOf")
  |  4:let trap = [? __x0__]
  |  5:if (= trap undefined) {
  |    6:app __x1__ = (target.GetPrototypeOf target)
  |    6:return [? __x1__]
  |  } else 0:{}
  |  7:app __x2__ = (Call trap handler (new [target]))
  |  7:let handlerProto = [? __x2__]
  |  8:if (! (|| (= (typeof handlerProto) Object) (= (typeof handlerProto) Null))) throw TypeError else 0:{}
  |  9:app __x3__ = (IsExtensible target)
  |  9:let extensibleTarget = [? __x3__]
  |  10:if (= extensibleTarget true) return handlerProto else 0:{}
  |  11:app __x4__ = (target.GetPrototypeOf target)
  |  11:let targetProto = [? __x4__]
  |  12:app __x5__ = (SameValue handlerProto targetProto)
  |  12:if (= __x5__ false) throw TypeError else 0:{}
  |  13:return handlerProto
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"getPrototypeOf"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[GetPrototypeOf]]().""",
    """        1. Let _handlerProto_ be ? Call(_trap_, _handler_, « _target_ »).""",
    """        1. If Type(_handlerProto_) is neither Object nor Null, throw a *TypeError* exception.""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. If _extensibleTarget_ is *true*, return _handlerProto_.""",
    """        1. Let _targetProto_ be ? _target_.[[GetPrototypeOf]]().""",
    """        1. If SameValue(_handlerProto_, _targetProto_) is *false*, throw a *TypeError* exception.""",
    """        1. Return _handlerProto_.""",
  )
}
