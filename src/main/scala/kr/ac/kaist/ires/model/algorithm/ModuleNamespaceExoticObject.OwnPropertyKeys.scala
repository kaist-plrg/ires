package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.OwnPropertyKeys` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "OwnPropertyKeys", Param("O", Normal), List())
  val ids = List(
    "sec-module-namespace-exotic-objects-ownpropertykeys",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let exports = (copy-obj O.Exports)
  |  1:app __x0__ = (OrdinaryOwnPropertyKeys O)
  |  1:let symbolKeys = [! __x0__]
  |  2:let __x1__ = symbolKeys
  |  2:let __x2__ = 0i
  |  2:while (< __x2__ __x1__.length) {
  |    let __x3__ = __x1__[__x2__]
  |    append __x3__ -> exports
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  3:return exports
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _exports_ be a copy of _O_.[[Exports]].""",
    """          1. Let _symbolKeys_ be ! OrdinaryOwnPropertyKeys(_O_).""",
    """          1. Append all the entries of _symbolKeys_ to the end of _exports_.""",
    """          1. Return _exports_.""",
  )
}
