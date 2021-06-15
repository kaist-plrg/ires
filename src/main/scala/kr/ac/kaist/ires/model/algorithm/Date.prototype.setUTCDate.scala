package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.setUTCDate` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.setUTCDate"""), List(Param("date", Normal)))
  val ids = List(
    "sec-date.prototype.setutcdate",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (thisTimeValue this)
  |  0:let t = [? __x0__]
  |  1:app __x1__ = (ToNumber date)
  |  1:let dt = [? __x1__]
  |  2:app __x2__ = (YearFromTime t)
  |  2:app __x3__ = (MonthFromTime t)
  |  2:app __x4__ = (MakeDay __x2__ __x3__ dt)
  |  2:app __x5__ = (TimeWithinDay t)
  |  2:app __x6__ = (MakeDate __x4__ __x5__)
  |  2:let newDate = __x6__
  |  3:app __x7__ = (TimeClip newDate)
  |  3:let v = __x7__
  |  4:this.DateValue = v
  |  5:return v
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _t_ be ? thisTimeValue(*this* value).""",
    """          1. Let _dt_ be ? ToNumber(_date_).""",
    """          1. Let _newDate_ be MakeDate(MakeDay(YearFromTime(_t_), MonthFromTime(_t_), _dt_), TimeWithinDay(_t_)).""",
    """          1. Let _v_ be TimeClip(_newDate_).""",
    """          1. Set the [[DateValue]] internal slot of this Date object to _v_.""",
    """          1. Return _v_.""",
  )
}
