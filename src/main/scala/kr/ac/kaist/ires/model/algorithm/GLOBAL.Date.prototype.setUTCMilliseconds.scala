package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setUTCMilliseconds` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCMilliseconds"""), List(Param("ms", Normal)))
  val ids = List(
    "sec-date.prototype.setutcmilliseconds",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber ms)
  |  1:let milli = [? __x1__]
  |  2:app __x2__ = (HourFromTime t)
  |  2:app __x3__ = (MinFromTime t)
  |  2:app __x4__ = (SecFromTime t)
  |  2:app __x5__ = (MakeTime __x2__ __x3__ __x4__ milli)
  |  2:let time = __x5__
  |  3:app __x6__ = (Day t)
  |  3:app __x7__ = (MakeDate __x6__ time)
  |  3:app __x8__ = (TimeClip __x7__)
  |  3:let v = __x8__
  |  4:this.DateValue = v
  |  5:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _milli_ be ? ToNumber(_ms_).""",
    """          1. Let _time_ be MakeTime(HourFromTime(_t_), MinFromTime(_t_), SecFromTime(_t_), _milli_).""",
    """          1. Let _v_ be TimeClip(MakeDate(Day(_t_), _time_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
