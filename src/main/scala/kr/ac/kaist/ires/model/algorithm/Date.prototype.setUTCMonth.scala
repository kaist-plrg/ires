package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setUTCMonth` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCMonth"""), List(Param("month", Normal), Param("date", Optional)))
  val ids = List(
    "sec-date.prototype.setutcmonth",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber month)
  |  1:let m = [? __x1__]
  |  3:if (= date absent) {
  |    app __x2__ = (DateFromTime t)
  |    let dt = __x2__
  |  } else {
  |    4:app __x3__ = (ToNumber date)
  |    4:let dt = [? __x3__]
  |  }
  |  5:app __x4__ = (YearFromTime t)
  |  5:app __x5__ = (MakeDay __x4__ m dt)
  |  5:app __x6__ = (TimeWithinDay t)
  |  5:app __x7__ = (MakeDate __x5__ __x6__)
  |  5:let newDate = __x7__
  |  6:app __x8__ = (TimeClip newDate)
  |  6:let v = __x8__
  |  7:this.DateValue = v
  |  8:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _m_ be ? ToNumber(_month_).""",
    """          1. If _date_ is not present, let _dt_ be DateFromTime(_t_).""",
    """          1. Else,""",
    """            1. Let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(YearFromTime(_t_), _m_, _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _v_ be TimeClip(_newDate_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
