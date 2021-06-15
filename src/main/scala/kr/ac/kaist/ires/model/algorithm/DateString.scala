package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DateString` extends Algo {
  val head = NormalHead("DateString", List(Param("tv", Normal)))
  val ids = List(
    "sec-datestring",
    "sec-date.prototype.tostring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof tv) Number)
  |  1:assert (! (= tv NaN))
  |  2:??? "Let id:{weekday} be the Name of the entry in link:{unhandled: sec-todatestring-day-names} with the Number WeekDay ( id:{tv} ) ."
  |  3:??? "Let id:{month} be the Name of the entry in link:{unhandled: sec-todatestring-month-names} with the Number MonthFromTime ( id:{tv} ) ."
  |  4:??? "Let id:{day} be the String representation of DateFromTime ( id:{tv} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  5:app __x0__ = (YearFromTime tv)
  |  5:let yv = __x0__
  |  6:if (! (< yv 0i)) let yearSign = "" else let yearSign = "-"
  |  7:??? "Let id:{year} be the String representation of abs ( ℝ ( id:{yv} ) ) , formatted as a decimal number ."
  |  8:app __x1__ = (StringPad year 4i "0" CONST_start)
  |  8:let paddedYear = [! __x1__]
  |  9:return (+ (+ (+ (+ (+ (+ (+ weekday " ") month) " ") day) " ") yearSign) paddedYear)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_tv_) is Number.""",
    """            1. Assert: _tv_ is not *NaN*.""",
    """            1. Let _weekday_ be the Name of the entry in <emu-xref href="#sec-todatestring-day-names"></emu-xref> with the Number WeekDay(_tv_).""",
    """            1. Let _month_ be the Name of the entry in <emu-xref href="#sec-todatestring-month-names"></emu-xref> with the Number MonthFromTime(_tv_).""",
    """            1. Let _day_ be the String representation of DateFromTime(_tv_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Let _yv_ be YearFromTime(_tv_).""",
    """            1. If _yv_ ≥ *+0*<sub>𝔽</sub>, let _yearSign_ be the empty String; otherwise, let _yearSign_ be *"-"*.""",
    """            1. Let _year_ be the String representation of abs(ℝ(_yv_)), formatted as a decimal number.""",
    """            1. Let _paddedYear_ be ! StringPad(_year_, *4*<sub>𝔽</sub>, *"0"*, ~start~).""",
    """            1. Return the string-concatenation of _weekday_, the code unit 0x0020 (SPACE), _month_, the code unit 0x0020 (SPACE), _day_, the code unit 0x0020 (SPACE), _yearSign_, and _paddedYear_.""",
  )
}
