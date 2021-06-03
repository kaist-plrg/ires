package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getMap[SYMBOL_species]` extends Algo {
  val head = BuiltinHead(parseRef("""getMap[SYMBOL_species]"""), List())
  val ids = List(
    "sec-get-map-@@species",
    "sec-properties-of-the-map-constructor",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the *this* value.""",
  )
}
