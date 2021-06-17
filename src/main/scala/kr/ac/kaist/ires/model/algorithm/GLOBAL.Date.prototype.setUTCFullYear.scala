package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setUTCFullYear` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCFullYear"""), List(Param("year", Normal), Param("month", Optional), Param("date", Optional)))
  val ids = List(
    "sec-date.prototype.setutcfullyear",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:if (= t NaN) t = 0i else 13:{}
  |  2:app __x1__ = (ToNumber year)
  |  2:let y = [? __x1__]
  |  3:if (= month absent) {
  |    app __x2__ = (MonthFromTime t)
  |    let m = __x2__
  |  } else {
  |    app __x3__ = (ToNumber month)
  |    let m = [? __x3__]
  |  }
  |  4:if (= date absent) {
  |    app __x4__ = (DateFromTime t)
  |    let dt = __x4__
  |  } else {
  |    app __x5__ = (ToNumber date)
  |    let dt = [? __x5__]
  |  }
  |  5:app __x6__ = (MakeDay y m dt)
  |  5:app __x7__ = (TimeWithinDay t)
  |  5:app __x8__ = (MakeDate __x6__ __x7__)
  |  5:let newDate = __x8__
  |  6:app __x9__ = (TimeClip newDate)
  |  6:let v = __x9__
  |  7:this.DateValue = v
  |  8:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. If _t_ is *NaN*, set _t_ to *+0*<sub>𝔽</sub>.""",
    """          1. Let _y_ be ? ToNumber(_year_).""",
    """          1. If _month_ is not present, let _m_ be MonthFromTime(_t_); otherwise, let _m_ be ? ToNumber(_month_).""",
    """          1. If _date_ is not present, let _dt_ be DateFromTime(_t_); otherwise, let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(_y_, _m_, _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _v_ be TimeClip(_newDate_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
