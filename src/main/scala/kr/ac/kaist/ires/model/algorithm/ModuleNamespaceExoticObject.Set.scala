package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleNamespaceExoticObject.Set` extends Algo {
  val head = MethodHead("ModuleNamespaceExoticObject", "Set", Param("O", Normal), List(Param("P", Normal), Param("V", Normal), Param("Receiver", Normal)))
  val ids = List(
    "sec-module-namespace-exotic-objects-set-p-v-receiver",
    "sec-module-namespace-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *false*.""",
  )
}
