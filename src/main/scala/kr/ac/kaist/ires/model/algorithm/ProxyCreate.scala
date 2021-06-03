package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ProxyCreate` extends Algo {
  val head = NormalHead("ProxyCreate", List(Param("target", Normal), Param("handler", Normal)))
  val ids = List(
    "sec-proxycreate",
    "sec-proxy-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= (typeof target) Object)) throw TypeError else 3:{}
  |  1:if (! (= (typeof handler) Object)) throw TypeError else 3:{}
  |  2:app __x0__ = (MakeBasicObject (new ["ProxyHandler", "ProxyTarget"]))
  |  2:let P = [! __x0__]
  |  4:app __x1__ = (IsCallable target)
  |  4:if (= __x1__ true) {
  |    5:P.Call = ProxyObjectDOTCall
  |    6:app __x2__ = (IsConstructor target)
  |    6:if (= __x2__ true) P.Construct = ProxyObjectDOTConstruct else 3:{}
  |  } else 3:{}
  |  8:P.ProxyTarget = target
  |  9:P.ProxyHandler = handler
  |  10:return P
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """        1. If Type(_handler_) is not Object, throw a *TypeError* exception.""",
    """        1. Let _P_ be ! MakeBasicObject(« [[ProxyHandler]], [[ProxyTarget]] »).""",
    """        1. Set _P_'s essential internal methods, except for [[Call]] and [[Construct]], to the definitions specified in <emu-xref href="#sec-proxy-object-internal-methods-and-internal-slots"></emu-xref>.""",
    """        1. If IsCallable(_target_) is *true*, then""",
    """          1. Set _P_.[[Call]] as specified in <emu-xref href="#sec-proxy-object-internal-methods-and-internal-slots-call-thisargument-argumentslist"></emu-xref>.""",
    """          1. If IsConstructor(_target_) is *true*, then""",
    """            1. Set _P_.[[Construct]] as specified in <emu-xref href="#sec-proxy-object-internal-methods-and-internal-slots-construct-argumentslist-newtarget"></emu-xref>.""",
    """        1. Set _P_.[[ProxyTarget]] to _target_.""",
    """        1. Set _P_.[[ProxyHandler]] to _handler_.""",
    """        1. Return _P_.""",
  )
}
