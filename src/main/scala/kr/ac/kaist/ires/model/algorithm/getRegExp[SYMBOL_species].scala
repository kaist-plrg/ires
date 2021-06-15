package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getRegExp[SYMBOL_species]` extends Algo {
  val head = BuiltinHead(parseRef("""getRegExp[SYMBOL_species]"""), List())
  val ids = List(
    "sec-get-regexp-@@species",
    "sec-properties-of-the-regexp-constructor",
    "sec-regexp-regular-expression-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the *this* value.""",
  )
}
