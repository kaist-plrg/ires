package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryIsExtensible` extends Algo {
  val head = NormalHead("OrdinaryIsExtensible", List(Param("O", Normal)))
  val ids = List(
    "sec-ordinaryisextensible",
    "sec-ordinary-object-internal-methods-and-internal-slots-isextensible",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""return O.Extensible""".stripMargin)
  val code = scala.Array[String](
    """          1. Return _O_.[[Extensible]].""",
  )
}
