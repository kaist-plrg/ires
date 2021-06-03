package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Number.prototype.valueOf` extends Algo {
  val head = BuiltinHead(parseRef("""Number.prototype.valueOf"""), List())
  val ids = List(
    "sec-number.prototype.valueof",
    "sec-properties-of-the-number-prototype-object",
    "sec-number-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisNumberValue this)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? thisNumberValue(*this* value).""",
  )
}
