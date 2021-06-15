package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Date.prototype.toUTCString` extends Algo {
  val head = BuiltinHead(parseRef("""Date.prototype.toUTCString"""), List())
  val ids = List(
    "sec-date.prototype.toutcstring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (thisTimeValue O)
  |  1:let tv = [? __x0__]
  |  2:if (= tv NaN) return "Invalid Date" else 13:{}
  |  3:??? "Let id:{weekday} be the Name of the entry in link:{unhandled: sec-todatestring-day-names} with the Number WeekDay ( id:{tv} ) ."
  |  4:??? "Let id:{month} be the Name of the entry in link:{unhandled: sec-todatestring-month-names} with the Number MonthFromTime ( id:{tv} ) ."
  |  5:??? "Let id:{day} be the String representation of DateFromTime ( id:{tv} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  6:app __x1__ = (YearFromTime tv)
  |  6:let yv = __x1__
  |  7:if (! (< yv 0i)) let yearSign = "" else let yearSign = "-"
  |  8:??? "Let id:{year} be the String representation of abs ( ℝ ( id:{yv} ) ) , formatted as a decimal number ."
  |  9:app __x2__ = (StringPad year 4i "0" CONST_start)
  |  9:let paddedYear = [! __x2__]
  |  10:app __x3__ = (TimeString tv)
  |  10:return (+ (+ (+ (+ (+ (+ (+ (+ (+ (+ weekday ",") " ") day) " ") month) " ") yearSign) paddedYear) " ") __x3__)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be this Date object.""",
    """          1. Let _tv_ be ? thisTimeValue(_O_).""",
    """          1. If _tv_ is *NaN*, return *"Invalid Date"*.""",
    """          1. Let _weekday_ be the Name of the entry in <emu-xref href="#sec-todatestring-day-names"></emu-xref> with the Number WeekDay(_tv_).""",
    """          1. Let _month_ be the Name of the entry in <emu-xref href="#sec-todatestring-month-names"></emu-xref> with the Number MonthFromTime(_tv_).""",
    """          1. Let _day_ be the String representation of DateFromTime(_tv_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """          1. Let _yv_ be YearFromTime(_tv_).""",
    """          1. If _yv_ ≥ *+0*<sub>𝔽</sub>, let _yearSign_ be the empty String; otherwise, let _yearSign_ be *"-"*.""",
    """          1. Let _year_ be the String representation of abs(ℝ(_yv_)), formatted as a decimal number.""",
    """          1. Let _paddedYear_ be ! StringPad(_year_, *4*<sub>𝔽</sub>, *"0"*, ~start~).""",
    """          1. Return the string-concatenation of _weekday_, *","*, the code unit 0x0020 (SPACE), _day_, the code unit 0x0020 (SPACE), _month_, the code unit 0x0020 (SPACE), _yearSign_, _paddedYear_, the code unit 0x0020 (SPACE), and TimeString(_tv_).""",
  )
}
