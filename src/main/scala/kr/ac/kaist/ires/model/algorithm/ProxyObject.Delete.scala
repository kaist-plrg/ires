package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.Delete` extends Algo {
  val head = MethodHead("ProxyObject", "Delete", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-delete-p",
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
  |  5:app __x1__ = (GetMethod handler "deleteProperty")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (target.Delete target P)
  |    7:return [? __x2__]
  |  } else 0:{}
  |  8:app __x3__ = (Call trap handler (new [target, P]))
  |  8:app __x4__ = (ToBoolean [? __x3__])
  |  8:let booleanTrapResult = [! __x4__]
  |  9:if (= booleanTrapResult false) return false else 0:{}
  |  10:app __x5__ = (target.GetOwnProperty target P)
  |  10:let targetDesc = [? __x5__]
  |  11:if (= targetDesc undefined) return true else 0:{}
  |  12:if (= targetDesc.Configurable false) throw TypeError else 0:{}
  |  13:app __x6__ = (IsExtensible target)
  |  13:let extensibleTarget = [? __x6__]
  |  14:if (= extensibleTarget false) throw TypeError else 0:{}
  |  15:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"deleteProperty"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[Delete]](_P_).""",
    """        1. Let _booleanTrapResult_ be ! ToBoolean(? Call(_trap_, _handler_, « _target_, _P_ »)).""",
    """        1. If _booleanTrapResult_ is *false*, return *false*.""",
    """        1. Let _targetDesc_ be ? _target_.[[GetOwnProperty]](_P_).""",
    """        1. If _targetDesc_ is *undefined*, return *true*.""",
    """        1. If _targetDesc_.[[Configurable]] is *false*, throw a *TypeError* exception.""",
    """        1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """        1. If _extensibleTarget_ is *false*, throw a *TypeError* exception.""",
    """        1. Return *true*.""",
  )
}
