package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Date.UTC` extends Algo {
  val head = BuiltinHead(parseRef("""Date.UTC"""), List(Param("year", Normal), Param("month", Optional), Param("date", Optional), Param("hours", Optional), Param("minutes", Optional), Param("seconds", Optional), Param("ms", Optional)))
  val ids = List(
    "sec-date.utc",
    "sec-properties-of-the-date-constructor",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToNumber year)
  |  0:let y = [? __x0__]
  |  1:if (! (= month absent)) {
  |    app __x1__ = (ToNumber month)
  |    let m = [? __x1__]
  |  } else let m = 0i
  |  2:if (! (= date absent)) {
  |    app __x2__ = (ToNumber date)
  |    let dt = [? __x2__]
  |  } else let dt = 1i
  |  3:if (! (= hours absent)) {
  |    app __x3__ = (ToNumber hours)
  |    let h = [? __x3__]
  |  } else let h = 0i
  |  4:if (! (= minutes absent)) {
  |    app __x4__ = (ToNumber minutes)
  |    let min = [? __x4__]
  |  } else let min = 0i
  |  5:if (! (= seconds absent)) {
  |    app __x5__ = (ToNumber seconds)
  |    let s = [? __x5__]
  |  } else let s = 0i
  |  6:if (! (= ms absent)) {
  |    app __x6__ = (ToNumber ms)
  |    let milli = [? __x6__]
  |  } else let milli = 0i
  |  8:if (= y NaN) let yr = NaN else {
  |    9:app __x7__ = (ToIntegerOrInfinity y)
  |    9:let yi = [! __x7__]
  |    10:if (&& (! (< yi 0i)) (! (< 99i yi))) let yr = (+ 1900i yi) else let yr = y
  |  }
  |  11:app __x8__ = (MakeDay yr m dt)
  |  11:app __x9__ = (MakeTime h min s milli)
  |  11:app __x10__ = (MakeDate __x8__ __x9__)
  |  11:app __x11__ = (TimeClip __x10__)
  |  11:return __x11__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _y_ be ? ToNumber(_year_).""",
    """          1. If _month_ is present, let _m_ be ? ToNumber(_month_); else let _m_ be *+0*<sub>𝔽</sub>.""",
    """          1. If _date_ is present, let _dt_ be ? ToNumber(_date_); else let _dt_ be *1*<sub>𝔽</sub>.""",
    """          1. If _hours_ is present, let _h_ be ? ToNumber(_hours_); else let _h_ be *+0*<sub>𝔽</sub>.""",
    """          1. If _minutes_ is present, let _min_ be ? ToNumber(_minutes_); else let _min_ be *+0*<sub>𝔽</sub>.""",
    """          1. If _seconds_ is present, let _s_ be ? ToNumber(_seconds_); else let _s_ be *+0*<sub>𝔽</sub>.""",
    """          1. If _ms_ is present, let _milli_ be ? ToNumber(_ms_); else let _milli_ be *+0*<sub>𝔽</sub>.""",
    """          1. If _y_ is *NaN*, let _yr_ be *NaN*.""",
    """          1. Else,""",
    """            1. Let _yi_ be ! ToIntegerOrInfinity(_y_).""",
    """            1. If 0 ≤ _yi_ ≤ 99, let _yr_ be *1900*<sub>𝔽</sub> + 𝔽(_yi_); otherwise, let _yr_ be _y_.""",
    """          1. Return TimeClip(MakeDate(MakeDay(_yr_, _m_, _dt_), MakeTime(_h_, _min_, _s_, _milli_))).""",
  )
}
