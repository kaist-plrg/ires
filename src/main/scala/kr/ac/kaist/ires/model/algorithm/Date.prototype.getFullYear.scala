package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.getFullYear` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.getFullYear"""), List())
  val ids = List(
    "sec-date.prototype.getfullyear",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:if (= t NaN) return NaN else 13:{}
  |  2:app __x1__ = (LocalTime t)
  |  2:app __x2__ = (YearFromTime __x1__)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. If _t_ is *NaN*, return *NaN*.""",
    """          1. Return YearFromTime(LocalTime(_t_)).""",
  )
}
