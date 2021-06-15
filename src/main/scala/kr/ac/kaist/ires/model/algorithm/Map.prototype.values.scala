package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Map.prototype.values` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.values"""), List())
  val ids = List(
    "sec-map.prototype.values",
    "sec-properties-of-the-map-prototype-object",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (CreateMapIterator M CONST_value)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Return ? CreateMapIterator(_M_, ~value~).""",
  )
}
