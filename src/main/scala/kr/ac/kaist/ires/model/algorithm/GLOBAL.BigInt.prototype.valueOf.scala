package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.BigInt.prototype.valueOf` extends Algo {
  val head = BuiltinHead(parseRef("""BigInt.prototype.valueOf"""), List())
  val ids = List(
    "sec-bigint.prototype.valueof",
    "sec-properties-of-the-bigint-prototype-object",
    "sec-bigint-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisBigIntValue this)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? thisBigIntValue(*this* value).""",
  )
}
