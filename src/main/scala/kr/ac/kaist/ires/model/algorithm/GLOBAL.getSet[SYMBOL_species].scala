package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.getSet[SYMBOL_species]` extends Algo {
  val head = BuiltinHead(parseRef("""getSet[SYMBOL_species]"""), List())
  val ids = List(
    "sec-get-set-@@species",
    "sec-properties-of-the-set-constructor",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the *this* value.""",
  )
}
