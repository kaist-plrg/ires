package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Object.prototype.valueOf` extends Algo {
  val head = BuiltinHead(parseRef("""Object.prototype.valueOf"""), List())
  val ids = List(
    "sec-object.prototype.valueof",
    "sec-properties-of-the-object-prototype-object",
    "sec-object-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? ToObject(*this* value).""",
  )
}
