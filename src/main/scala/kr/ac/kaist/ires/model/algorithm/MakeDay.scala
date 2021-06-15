package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MakeDay` extends Algo {
  val head = NormalHead("MakeDay", List(Param("year", Normal), Param("month", Normal), Param("date", Normal)))
  val ids = List(
    "sec-makeday",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:if (|| (|| (= year Infinity) (= year -Infinity)) (|| (|| (= month Infinity) (= month -Infinity)) (|| (= date Infinity) (= date -Infinity)))) return NaN else 2:{}
  |  1:app __x0__ = (ToIntegerOrInfinity year)
  |  1:let y = [! __x0__]
  |  2:app __x1__ = (ToIntegerOrInfinity month)
  |  2:let m = [! __x1__]
  |  3:app __x2__ = (ToIntegerOrInfinity date)
  |  3:let dt = [! __x2__]
  |  4:app __x3__ = (floor (/ m 12i))
  |  4:let ym = (+ y __x3__)
  |  5:if (|| (= ym Infinity) (= ym -Infinity)) return NaN else 2:{}
  |  6:let mn = (%% m 12i)
  |  7:??? "Find a finite time value id:{t} such that YearFromTime ( id:{t} ) is id:{ym} and MonthFromTime ( id:{t} ) is id:{mn} and DateFromTime ( id:{t} ) is value:{1} ; but if this is not possible ( because some argument is out of range ) , return value:{NaN} ."
  |  8:app __x4__ = (Day t)
  |  8:return (- (+ __x4__ dt) 1i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If _year_ is not finite or _month_ is not finite or _date_ is not finite, return *NaN*.""",
    """          1. Let _y_ be 𝔽(! ToIntegerOrInfinity(_year_)).""",
    """          1. Let _m_ be 𝔽(! ToIntegerOrInfinity(_month_)).""",
    """          1. Let _dt_ be 𝔽(! ToIntegerOrInfinity(_date_)).""",
    """          1. Let _ym_ be _y_ + 𝔽(floor(ℝ(_m_) / 12)).""",
    """          1. If _ym_ is not finite, return *NaN*.""",
    """          1. Let _mn_ be 𝔽(ℝ(_m_) modulo 12).""",
    """          1. Find a finite time value _t_ such that YearFromTime(_t_) is _ym_ and MonthFromTime(_t_) is _mn_ and DateFromTime(_t_) is *1*<sub>𝔽</sub>; but if this is not possible (because some argument is out of range), return *NaN*.""",
    """          1. Return Day(_t_) + _dt_ - *1*<sub>𝔽</sub>.""",
  )
}
