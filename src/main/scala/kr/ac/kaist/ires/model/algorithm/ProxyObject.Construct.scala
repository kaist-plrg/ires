package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.Construct` extends Algo {
  val head = MethodHead("ProxyObject", "Construct", Param("O", Normal), List(Param("argumentsList", Normal), Param("newTarget", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-construct-argumentslist-newtarget",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let handler = O.ProxyHandler
  |  1:if (= handler null) throw TypeError else 13:{}
  |  2:assert (= (typeof handler) Object)
  |  3:let target = O.ProxyTarget
  |  4:app __x0__ = (IsConstructor target)
  |  4:assert (= __x0__ true)
  |  5:app __x1__ = (GetMethod handler "construct")
  |  5:let trap = [? __x1__]
  |  6:if (= trap undefined) {
  |    7:app __x2__ = (Construct target argumentsList newTarget)
  |    7:return [? __x2__]
  |  } else 13:{}
  |  8:app __x3__ = (CreateArrayFromList argumentsList)
  |  8:let argArray = [! __x3__]
  |  9:app __x4__ = (Call trap handler (new [target, argArray, newTarget]))
  |  9:let newObj = [? __x4__]
  |  10:if (! (= (typeof newObj) Object)) throw TypeError else 13:{}
  |  11:return newObj
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Assert: IsConstructor(_target_) is *true*.""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"construct"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? Construct(_target_, _argumentsList_, _newTarget_).""",
    """        1. Let _argArray_ be ! CreateArrayFromList(_argumentsList_).""",
    """        1. Let _newObj_ be ? Call(_trap_, _handler_, « _target_, _argArray_, _newTarget_ »).""",
    """        1. If Type(_newObj_) is not Object, throw a *TypeError* exception.""",
    """        1. Return _newObj_.""",
  )
}
