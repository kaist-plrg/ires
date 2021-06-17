package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Boolean.prototype.valueOf` extends Algo {
  val head = BuiltinHead(parseRef("""Boolean.prototype.valueOf"""), List())
  val ids = List(
    "sec-boolean.prototype.valueof",
    "sec-properties-of-the-boolean-prototype-object",
    "sec-boolean-objects",
    "sec-fundamental-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisBooleanValue this)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? thisBooleanValue(*this* value).""",
  )
}
