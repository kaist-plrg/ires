package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setHours` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setHours"""), List(Param("hour", Normal), Param("min", Optional), Param("sec", Optional), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.sethours",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber hour)
  |  1:let h = [? __x2__]
  |  2:if (= min absent) {
  |    app __x3__ = (MinFromTime t)
  |    let m = __x3__
  |  } else {
  |    app __x4__ = (ToNumber min)
  |    let m = [? __x4__]
  |  }
  |  3:if (= sec absent) {
  |    app __x5__ = (SecFromTime t)
  |    let s = __x5__
  |  } else {
  |    app __x6__ = (ToNumber sec)
  |    let s = [? __x6__]
  |  }
  |  4:if (= ms absent) {
  |    app __x7__ = (msFromTime t)
  |    let milli = __x7__
  |  } else {
  |    app __x8__ = (ToNumber ms)
  |    let milli = [? __x8__]
  |  }
  |  5:app __x9__ = (Day t)
  |  5:app __x10__ = (MakeTime h m s milli)
  |  5:app __x11__ = (MakeDate __x9__ __x10__)
  |  5:let date = __x11__
  |  6:app __x12__ = (UTC date)
  |  6:app __x13__ = (TimeClip __x12__)
  |  6:let u = __x13__
  |  7:this.DateValue = u
  |  8:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Let _h_ be ? ToNumber(_hour_).""",
    """          1. If _min_ is not present, let _m_ be MinFromTime(_t_); otherwise, let _m_ be ? ToNumber(_min_).""",
    """          1. If _sec_ is not present, let _s_ be SecFromTime(_t_); otherwise, let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_); otherwise, let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _date_ be MakeDate(Day(_t_), MakeTime(_h_, _m_, _s_, _milli_)).""",
    """          1. Let _u_ be TimeClip(UTC(_date_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
