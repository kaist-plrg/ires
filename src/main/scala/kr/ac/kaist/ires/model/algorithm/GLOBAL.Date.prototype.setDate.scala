package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.prototype.setDate` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setDate"""), List(Param("date", Normal)))
  val ids = List(
    "sec-date.prototype.setdate",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:app __x1__ = (LocalTime [? __x0__])
  |  0:let t = __x1__
  |  1:app __x2__ = (ToNumber date)
  |  1:let dt = [? __x2__]
  |  2:app __x3__ = (YearFromTime t)
  |  2:app __x4__ = (MonthFromTime t)
  |  2:app __x5__ = (MakeDay __x3__ __x4__ dt)
  |  2:app __x6__ = (TimeWithinDay t)
  |  2:app __x7__ = (MakeDate __x5__ __x6__)
  |  2:let newDate = __x7__
  |  3:app __x8__ = (UTC newDate)
  |  3:app __x9__ = (TimeClip __x8__)
  |  3:let u = __x9__
  |  4:this.DateValue = u
  |  5:return u
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be LocalTime(? thisTimeValue(*this* value)).""",
    """          1. Let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(YearFromTime(_t_), MonthFromTime(_t_), _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _u_ be TimeClip(UTC(_newDate_)).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _u_.""",
    """          1. Return _u_.""",
  )
}
