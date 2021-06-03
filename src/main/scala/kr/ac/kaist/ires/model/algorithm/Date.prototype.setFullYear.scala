package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setFullYear` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setFullYear"""), List(Param("year", Normal), Param("month", Optional), Param("date", Optional)))
  val ids = List(
    "sec-date.prototype.setfullyear",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:if (= t NaN) t = 0i else {
  |    app __x1__ = (LocalTime t)
  |    t = __x1__
  |  }
  |  2:app __x2__ = (ToNumber year)
  |  2:let y = [? __x2__]
  |  3:if (= month absent) {
  |    app __x3__ = (MonthFromTime t)
  |    let m = __x3__
  |  } else {
  |    app __x4__ = (ToNumber month)
  |    let m = [? __x4__]
  |  }
  |  4:if (= date absent) {
  |    app __x5__ = (DateFromTime t)
  |    let dt = __x5__
  |  } else {
  |    app __x6__ = (ToNumber date)
  |    let dt = [? __x6__]
  |  }
  |  5:app __x7__ = (MakeDay y m dt)
  |  5:app __x8__ = (TimeWithinDay t)
  |  5:app __x9__ = (MakeDate __x7__ __x8__)
  |  5:let newDate = __x9__
  |  6:app __x10__ = (UTC newDate)
  |  6:app __x11__ = (TimeClip __x10__)
  |  6:let u = __x11__
  |  7:this.DateValue = u
  |  8:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. If _t_ is *NaN*, set _t_ to *+0*<sub>𝔽</sub>; otherwise, set _t_ to LocalTime(_t_).""",
    """          1. Let _y_ be ? ToNumber(_year_).""",
    """          1. If _month_ is not present, let _m_ be MonthFromTime(_t_); otherwise, let _m_ be ? ToNumber(_month_).""",
    """          1. If _date_ is not present, let _dt_ be DateFromTime(_t_); otherwise, let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(_y_, _m_, _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _u_ be TimeClip(UTC(_newDate_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
