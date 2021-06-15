package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getArray[SYMBOL_species]` extends Algo {
  val head = BuiltinHead(parseRef("""getArray[SYMBOL_species]"""), List())
  val ids = List(
    "sec-get-array-@@species",
    "sec-properties-of-the-array-constructor",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the *this* value.""",
  )
}
