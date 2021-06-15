package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getSharedArrayBuffer[SYMBOL_species]` extends Algo {
  val head = BuiltinHead(parseRef("""getSharedArrayBuffer[SYMBOL_species]"""), List())
  val ids = List(
    "sec-sharedarraybuffer-@@species",
    "sec-properties-of-the-sharedarraybuffer-constructor",
    "sec-sharedarraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""return this""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the *this* value.""",
  )
}
