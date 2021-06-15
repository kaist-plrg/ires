package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.Delete` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "Delete", Param("O", Normal), List(Param("P", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-delete-p",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= (typeof P) Symbol) {
  |    2:app __x1__ = (OrdinaryDelete O P)
  |    2:return [? __x1__]
  |  } else 0:{}
  |  3:let exports = O.Exports
  |  4:if (contains exports P) return false else 0:{}
  |  5:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsPropertyKey(_P_) is *true*.""",
    """          1. If Type(_P_) is Symbol, then""",
    """            1. Return ? OrdinaryDelete(_O_, _P_).""",
    """          1. Let _exports_ be _O_.[[Exports]].""",
    """          1. If _P_ is an element of _exports_, return *false*.""",
    """          1. Return *true*.""",
  )
}
