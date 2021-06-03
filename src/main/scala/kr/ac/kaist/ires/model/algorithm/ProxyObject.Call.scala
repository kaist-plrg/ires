package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyObject.Call` extends Algo {
  val head = MethodHead("ProxyObject", "Call", Param("O", Normal), List(Param("thisArgument", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-proxy-object-internal-methods-and-internal-slots-call-thisargument-argumentslist",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let handler = O.ProxyHandler
  |  1:if (= handler null) throw TypeError else 13:{}
  |  2:assert (= (typeof handler) Object)
  |  3:let target = O.ProxyTarget
  |  4:app __x0__ = (GetMethod handler "apply")
  |  4:let trap = [? __x0__]
  |  5:if (= trap undefined) {
  |    6:app __x1__ = (Call target thisArgument argumentsList)
  |    6:return [? __x1__]
  |  } else 13:{}
  |  7:app __x2__ = (CreateArrayFromList argumentsList)
  |  7:let argArray = [! __x2__]
  |  8:app __x3__ = (Call trap handler (new [target, thisArgument, argArray]))
  |  8:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _handler_ be _O_.[[ProxyHandler]].""",
    """        1. If _handler_ is *null*, throw a *TypeError* exception.""",
    """        1. Assert: Type(_handler_) is Object.""",
    """        1. Let _target_ be _O_.[[ProxyTarget]].""",
    """        1. Let _trap_ be ? GetMethod(_handler_, *"apply"*).""",
    """        1. If _trap_ is *undefined*, then""",
    """          1. Return ? Call(_target_, _thisArgument_, _argumentsList_).""",
    """        1. Let _argArray_ be ! CreateArrayFromList(_argumentsList_).""",
    """        1. Return ? Call(_trap_, _handler_, « _target_, _thisArgument_, _argArray_ »).""",
  )
}
