package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setMilliseconds` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setMilliseconds"""), List(Param("ms", Normal)))
  val ids = List(
    "sec-date.prototype.setmilliseconds",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber ms)
  |  1:ms = [? __x2__]
  |  2:app __x3__ = (HourFromTime t)
  |  2:app __x4__ = (MinFromTime t)
  |  2:app __x5__ = (SecFromTime t)
  |  2:app __x6__ = (MakeTime __x3__ __x4__ __x5__ ms)
  |  2:let time = __x6__
  |  3:app __x7__ = (Day t)
  |  3:app __x8__ = (MakeDate __x7__ time)
  |  3:app __x9__ = (UTC __x8__)
  |  3:app __x10__ = (TimeClip __x9__)
  |  3:let u = __x10__
  |  4:this.DateValue = u
  |  5:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Set _ms_ to ? ToNumber(_ms_).""",
    """          1. Let _time_ be MakeTime(HourFromTime(_t_), MinFromTime(_t_), SecFromTime(_t_), _ms_).""",
    """          1. Let _u_ be TimeClip(UTC(MakeDate(Day(_t_), _time_))).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
