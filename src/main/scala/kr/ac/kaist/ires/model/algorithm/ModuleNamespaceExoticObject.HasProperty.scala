package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.HasProperty` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "HasProperty", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-hasproperty-p",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof P) Symbol) {
  |    app __x0__ = (OrdinaryHasProperty O P)
  |    return __x0__
  |  } else 0:{}
  |  1:let exports = O.Exports
  |  2:if (contains exports P) return true else 0:{}
  |  3:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_P_) is Symbol, return OrdinaryHasProperty(_O_, _P_).""",
    """          1. Let _exports_ be _O_.[[Exports]].""",
    """          1. If _P_ is an element of _exports_, return *true*.""",
    """          1. Return *false*.""",
  )
}
