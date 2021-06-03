package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.trimStart` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.trimStart"""), List())
  val ids = List(
    "sec-string.prototype.trimstart",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (TrimString S CONST_start)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Return ? TrimString(_S_, ~start~).""",
  )
}
