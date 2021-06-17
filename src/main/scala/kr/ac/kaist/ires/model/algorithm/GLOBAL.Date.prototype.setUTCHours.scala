package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setUTCHours` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCHours"""), List(Param("hour", Normal), Param("min", Optional), Param("sec", Optional), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.setutchours",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber hour)
  |  1:let h = [? __x1__]
  |  2:if (= min absent) {
  |    app __x2__ = (MinFromTime t)
  |    let m = __x2__
  |  } else {
  |    app __x3__ = (ToNumber min)
  |    let m = [? __x3__]
  |  }
  |  3:if (= sec absent) {
  |    app __x4__ = (SecFromTime t)
  |    let s = __x4__
  |  } else {
  |    app __x5__ = (ToNumber sec)
  |    let s = [? __x5__]
  |  }
  |  4:if (= ms absent) {
  |    app __x6__ = (msFromTime t)
  |    let milli = __x6__
  |  } else {
  |    app __x7__ = (ToNumber ms)
  |    let milli = [? __x7__]
  |  }
  |  5:app __x8__ = (Day t)
  |  5:app __x9__ = (MakeTime h m s milli)
  |  5:app __x10__ = (MakeDate __x8__ __x9__)
  |  5:let newDate = __x10__
  |  6:app __x11__ = (TimeClip newDate)
  |  6:let v = __x11__
  |  7:this.DateValue = v
  |  8:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _h_ be ? ToNumber(_hour_).""",
    """          1. If _min_ is not present, let _m_ be MinFromTime(_t_); otherwise, let _m_ be ? ToNumber(_min_).""",
    """          1. If _sec_ is not present, let _s_ be SecFromTime(_t_); otherwise, let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_); otherwise, let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _newDate_ be MakeDate(Day(_t_), MakeTime(_h_, _m_, _s_, _milli_)).""",
    """          1. Let _v_ be TimeClip(_newDate_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
