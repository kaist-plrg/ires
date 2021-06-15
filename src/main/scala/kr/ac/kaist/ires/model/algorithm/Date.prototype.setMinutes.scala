package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setMinutes` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setMinutes"""), List(Param("min", Normal), Param("sec", Optional), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.setminutes",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber min)
  |  1:let m = [? __x2__]
  |  2:if (= sec absent) {
  |    app __x3__ = (SecFromTime t)
  |    let s = __x3__
  |  } else {
  |    app __x4__ = (ToNumber sec)
  |    let s = [? __x4__]
  |  }
  |  3:if (= ms absent) {
  |    app __x5__ = (msFromTime t)
  |    let milli = __x5__
  |  } else {
  |    app __x6__ = (ToNumber ms)
  |    let milli = [? __x6__]
  |  }
  |  4:app __x7__ = (Day t)
  |  4:app __x8__ = (HourFromTime t)
  |  4:app __x9__ = (MakeTime __x8__ m s milli)
  |  4:app __x10__ = (MakeDate __x7__ __x9__)
  |  4:let date = __x10__
  |  5:app __x11__ = (UTC date)
  |  5:app __x12__ = (TimeClip __x11__)
  |  5:let u = __x12__
  |  6:this.DateValue = u
  |  7:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Let _m_ be ? ToNumber(_min_).""",
    """          1. If _sec_ is not present, let _s_ be SecFromTime(_t_); otherwise, let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_); otherwise, let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _date_ be MakeDate(Day(_t_), MakeTime(HourFromTime(_t_), _m_, _s_, _milli_)).""",
    """          1. Let _u_ be TimeClip(UTC(_date_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
