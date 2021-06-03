package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setUTCSeconds` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCSeconds"""), List(Param("sec", Normal), Param("ms", Optional)))
  val ids = List(
    "sec-date.prototype.setutcseconds",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber sec)
  |  1:let s = [? __x1__]
  |  3:if (= ms absent) {
  |    app __x2__ = (msFromTime t)
  |    let milli = __x2__
  |  } else {
  |    4:app __x3__ = (ToNumber ms)
  |    4:let milli = [? __x3__]
  |  }
  |  5:app __x4__ = (Day t)
  |  5:app __x5__ = (HourFromTime t)
  |  5:app __x6__ = (MinFromTime t)
  |  5:app __x7__ = (MakeTime __x5__ __x6__ s milli)
  |  5:app __x8__ = (MakeDate __x4__ __x7__)
  |  5:let date = __x8__
  |  6:app __x9__ = (TimeClip date)
  |  6:let v = __x9__
  |  7:this.DateValue = v
  |  8:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _s_ be ? ToNumber(_sec_).""",
    """          1. If _ms_ is not present, let _milli_ be msFromTime(_t_).""",
    """          1. Else,""",
    """            1. Let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _date_ be MakeDate(Day(_t_), MakeTime(HourFromTime(_t_), MinFromTime(_t_), _s_, _milli_)).""",
    """          1. Let _v_ be TimeClip(_date_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
