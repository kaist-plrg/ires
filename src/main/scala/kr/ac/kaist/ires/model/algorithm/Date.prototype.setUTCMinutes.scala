package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setUTCMinutes` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCMinutes"""), List(Param("min", Normal), Param("sec", Optional), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.setutcminutes",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber min)
  |  1:let m = [? __x1__]
  |  3:if (= sec absent) {
  |    app __x2__ = (SecFromTime t)
  |    let s = __x2__
  |  } else {
  |    4:app __x3__ = (ToNumber sec)
  |    4:let s = [? __x3__]
  |  }
  |  6:if (= ms absent) {
  |    app __x4__ = (msFromTime t)
  |    let milli = __x4__
  |  } else {
  |    7:app __x5__ = (ToNumber ms)
  |    7:let milli = [? __x5__]
  |  }
  |  8:app __x6__ = (Day t)
  |  8:app __x7__ = (HourFromTime t)
  |  8:app __x8__ = (MakeTime __x7__ m s milli)
  |  8:app __x9__ = (MakeDate __x6__ __x8__)
  |  8:let date = __x9__
  |  9:app __x10__ = (TimeClip date)
  |  9:let v = __x10__
  |  10:this.DateValue = v
  |  11:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _m_ be ? ToNumber(_min_).""",
    """          1. If _sec_ is not present, let _s_ be SecFromTime(_t_).""",
    """          1. Else,""",
    """            1. Let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_).""",
    """          1. Else,""",
    """            1. Let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _date_ be MakeDate(Day(_t_), MakeTime(HourFromTime(_t_), _m_, _s_, _milli_)).""",
    """          1. Let _v_ be TimeClip(_date_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
