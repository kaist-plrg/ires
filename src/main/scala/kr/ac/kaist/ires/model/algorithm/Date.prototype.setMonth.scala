package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setMonth` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setMonth"""), List(Param("month", Normal), Param("date", Optional)))
  val ids = List(
    "sec-date.prototype.setmonth",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber month)
  |  1:let m = [? __x2__]
  |  2:if (= date absent) {
  |    app __x3__ = (DateFromTime t)
  |    let dt = __x3__
  |  } else {
  |    app __x4__ = (ToNumber date)
  |    let dt = [? __x4__]
  |  }
  |  3:app __x5__ = (YearFromTime t)
  |  3:app __x6__ = (MakeDay __x5__ m dt)
  |  3:app __x7__ = (TimeWithinDay t)
  |  3:app __x8__ = (MakeDate __x6__ __x7__)
  |  3:let newDate = __x8__
  |  4:app __x9__ = (UTC newDate)
  |  4:app __x10__ = (TimeClip __x9__)
  |  4:let u = __x10__
  |  5:this.DateValue = u
  |  6:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Let _m_ be ? ToNumber(_month_).""",
    """          1. If _date_ is not present, let _dt_ be DateFromTime(_t_); otherwise, let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(YearFromTime(_t_), _m_, _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _u_ be TimeClip(UTC(_newDate_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
