package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TimeZoneString` extends Algo {
  val head = NormalHead("TimeZoneString", List(Param("tv", Normal)))
  val ids = List(
    "sec-timezoneestring",
    "sec-date.prototype.tostring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof tv) Number)
  |  1:assert (! (= tv NaN))
  |  2:app __x0__ = (LocalTZA tv true)
  |  2:let offset = __x0__
  |  6:if (! (< offset 0i)) {
  |    4:let offsetSign = "+"
  |    5:let absOffset = offset
  |  } else {
  |    7:let offsetSign = "-"
  |    8:let absOffset = (- offset)
  |  }
  |  9:??? "Let id:{offsetMin} be the String representation of MinFromTime ( id:{absOffset} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  10:??? "Let id:{offsetHour} be the String representation of HourFromTime ( id:{absOffset} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  11:??? "Let id:{tzName} be an implementation - defined string that is either the empty String or the string - concatenation of the code unit 0x0020 ( SPACE ) , the code unit 0x0028 ( LEFT PARENTHESIS ) , an implementation - defined timezone name , and the code unit 0x0029 ( RIGHT PARENTHESIS ) ."
  |  12:return (+ (+ (+ offsetSign offsetHour) offsetMin) tzName)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_tv_) is Number.""",
    """            1. Assert: _tv_ is not *NaN*.""",
    """            1. Let _offset_ be LocalTZA(_tv_, *true*).""",
    """            1. If _offset_ ≥ *+0*<sub>𝔽</sub>, then""",
    """              1. Let _offsetSign_ be *"+"*.""",
    """              1. Let _absOffset_ be _offset_.""",
    """            1. Else,""",
    """              1. Let _offsetSign_ be *"-"*.""",
    """              1. Let _absOffset_ be -_offset_.""",
    """            1. Let _offsetMin_ be the String representation of MinFromTime(_absOffset_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Let _offsetHour_ be the String representation of HourFromTime(_absOffset_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Let _tzName_ be an implementation-defined string that is either the empty String or the string-concatenation of the code unit 0x0020 (SPACE), the code unit 0x0028 (LEFT PARENTHESIS), an implementation-defined timezone name, and the code unit 0x0029 (RIGHT PARENTHESIS).""",
    """            1. Return the string-concatenation of _offsetSign_, _offsetHour_, _offsetMin_, and _tzName_.""",
  )
}
