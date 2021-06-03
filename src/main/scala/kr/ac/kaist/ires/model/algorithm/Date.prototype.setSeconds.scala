package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setSeconds` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setSeconds"""), List(Param("sec", Normal), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.setseconds",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber sec)
  |  1:let s = [? __x2__]
  |  2:if (= ms absent) {
  |    app __x3__ = (msFromTime t)
  |    let milli = __x3__
  |  } else {
  |    app __x4__ = (ToNumber ms)
  |    let milli = [? __x4__]
  |  }
  |  3:app __x5__ = (Day t)
  |  3:app __x6__ = (HourFromTime t)
  |  3:app __x7__ = (MinFromTime t)
  |  3:app __x8__ = (MakeTime __x6__ __x7__ s milli)
  |  3:app __x9__ = (MakeDate __x5__ __x8__)
  |  3:let date = __x9__
  |  4:app __x10__ = (UTC date)
  |  4:app __x11__ = (TimeClip __x10__)
  |  4:let u = __x11__
  |  5:this.DateValue = u
  |  6:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_); otherwise, let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _date_ be MakeDate(Day(_t_), MakeTime(HourFromTime(_t_), MinFromTime(_t_), _s_, _milli_)).""",
    """          1. Let _u_ be TimeClip(UTC(_date_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
