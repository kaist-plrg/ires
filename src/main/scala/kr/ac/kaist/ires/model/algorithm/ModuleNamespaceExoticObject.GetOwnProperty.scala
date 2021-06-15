package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.GetOwnProperty` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "GetOwnProperty", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-getownproperty-p",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:if (= (typeof P) Symbol) {
  |    app __x0__ = (OrdinaryGetOwnProperty O P)
  |    return __x0__
  |  } else 0:{}
  |  1:let exports = O.Exports
  |  2:if (! (contains exports P)) return undefined else 0:{}
  |  3:app __x1__ = (O.Get O P O)
  |  3:let value = [? __x1__]
  |  4:return (new PropertyDescriptor("Value" -> value, "Writable" -> true, "Enumerable" -> true, "Configurable" -> false))
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If Type(_P_) is Symbol, return OrdinaryGetOwnProperty(_O_, _P_).""",
    """          1. Let _exports_ be _O_.[[Exports]].""",
    """          1. If _P_ is not an element of _exports_, return *undefined*.""",
    """          1. Let _value_ be ? _O_.[[Get]](_P_, _O_).""",
    """          1. Return PropertyDescriptor { [[Value]]: _value_, [[Writable]]: *true*, [[Enumerable]]: *true*, [[Configurable]]: *false* }.""",
  )
}
