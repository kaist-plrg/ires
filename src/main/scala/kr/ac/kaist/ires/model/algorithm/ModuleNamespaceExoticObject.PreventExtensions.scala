package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.PreventExtensions` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "PreventExtensions", Param("O", Normal), List())
  val ids = List(
    "sec-module-namespace-exotic-objects-preventextensions",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""return true""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *true*.""",
  )
}
