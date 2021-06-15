package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.SetPrototypeOf` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "SetPrototypeOf", Param("O", Normal), List(Param("V", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-setprototypeof-v",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (SetImmutablePrototype O V)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? SetImmutablePrototype(_O_, _V_).""",
  )
}
