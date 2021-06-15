package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.getMinutes` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.getMinutes"""), List())
  val ids = List(
    "sec-date.prototype.getminutes",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:if (= t NaN) return NaN else 13:{}
  |  2:app __x1__ = (LocalTime t)
  |  2:app __x2__ = (MinFromTime __x1__)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. If _t_ is *NaN*, return *NaN*.""",
    """          1. Return MinFromTime(LocalTime(_t_)).""",
  )
}
