package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OrdinaryGetPrototypeOf` extends Algo {
  val head = NormalHead("OrdinaryGetPrototypeOf", List(Param("O", Normal)))
  val ids = List(
    "sec-ordinarygetprototypeof",
    "sec-ordinary-object-internal-methods-and-internal-slots-getprototypeof",
    "sec-ordinary-object-internal-methods-and-internal-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""return O.Prototype""".stripMargin)
  val code = scala.Array[String](
    """          1. Return _O_.[[Prototype]].""",
  )
}
