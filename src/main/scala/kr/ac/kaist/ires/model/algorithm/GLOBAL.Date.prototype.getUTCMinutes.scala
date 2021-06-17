package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.getUTCMinutes` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.getUTCMinutes"""), List())
  val ids = List(
    "sec-date.prototype.getutcminutes",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:if (= t NaN) return NaN else 13:{}
  |  2:app __x1__ = (MinFromTime t)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. If _t_ is *NaN*, return *NaN*.""",
    """          1. Return MinFromTime(_t_).""",
  )
}
