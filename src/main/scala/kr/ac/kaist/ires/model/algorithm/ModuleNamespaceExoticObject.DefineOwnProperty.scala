package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "DefineOwnProperty", Param("O", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-defineownproperty-p-desc",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof P) Symbol) {
  |    app __x0__ = (OrdinaryDefineOwnProperty O P Desc)
  |    return __x0__
  |  } else 0:{}
  |  1:app __x1__ = (O.GetOwnProperty O P)
  |  1:let current = [? __x1__]
  |  2:if (= current undefined) return false else 0:{}
  |  3:if (= Desc.Configurable true) return false else 0:{}
  |  4:if (= Desc.Enumerable false) return false else 0:{}
  |  5:app __x2__ = (IsAccessorDescriptor Desc)
  |  5:if (= [! __x2__] true) return false else 0:{}
  |  6:if (= Desc.Writable false) return false else 0:{}
  |  7:if (! (= Desc.Value absent)) {
  |    app __x3__ = (SameValue Desc.Value current.Value)
  |    return __x3__
  |  } else 0:{}
  |  8:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_P_) is Symbol, return OrdinaryDefineOwnProperty(_O_, _P_, _Desc_).""",
    """          1. Let _current_ be ? _O_.[[GetOwnProperty]](_P_).""",
    """          1. If _current_ is *undefined*, return *false*.""",
    """          1. If _Desc_.[[Configurable]] is present and has value *true*, return *false*.""",
    """          1. If _Desc_.[[Enumerable]] is present and has value *false*, return *false*.""",
    """          1. If ! IsAccessorDescriptor(_Desc_) is *true*, return *false*.""",
    """          1. If _Desc_.[[Writable]] is present and has value *false*, return *false*.""",
    """          1. If _Desc_.[[Value]] is present, return SameValue(_Desc_.[[Value]], _current_.[[Value]]).""",
    """          1. Return *true*.""",
  )
}
