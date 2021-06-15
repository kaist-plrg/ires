package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.PreventExtensions` extends Algo {
  val head = MethodHead("ProxyObject", "PreventExtensions", Param("O", Normal), List())
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-preventextensions",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let handler = O.ProxyHandler
  |  1:if (= handler null) throw TypeError else 0:{}
  |  2:assert (= (typeof handler) Object)
  |  3:let target = O.ProxyTarget
  |  4:app __x0__ = (GetMethod handler "preventExtensions")
  |  4:let trap = [? __x0__]
  |  5:if (= trap undefined) {
  |    6:app __x1__ = (target.PreventExtensions target)
  |    6:return [? __x1__]
  |  } else 0:{}
  |  7:app __x2__ = (Call trap handler (new [target]))
  |  7:app __x3__ = (ToBoolean [? __x2__])
  |  7:let booleanTrapResult = [! __x3__]
  |  8:if (= booleanTrapResult true) {
  |    9:app __x4__ = (IsExtensible target)
  |    9:let extensibleTarget = [? __x4__]
  |    10:if (= extensibleTarget true) throw TypeError else 0:{}
  |  } else 0:{}
  |  11:return booleanTrapResult
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"preventExtensions"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? _target_.[[PreventExtensions]]().""",
    """        1. Let _booleanTrapResult_ be ! ToBoolean(? Call(_trap_, _handler_, « _target_ »)).""",
    """        1. If _booleanTrapResult_ is *true*, then""",
    """          1. Let _extensibleTarget_ be ? IsExtensible(_target_).""",
    """          1. If _extensibleTarget_ is *true*, throw a *TypeError* exception.""",
    """        1. Return _booleanTrapResult_.""",
  )
}
