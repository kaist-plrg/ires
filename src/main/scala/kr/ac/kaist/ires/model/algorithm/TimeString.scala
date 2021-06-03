package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TimeString` extends Algo {
  val head = NormalHead("TimeString", List(Param("tv", Normal)))
  val ids = List(
    "sec-timestring",
    "sec-date.prototype.tostring",
    "sec-properties-of-the-date-prototype-object",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof tv) Number)
  |  1:assert (! (= tv NaN))
  |  2:??? "Let id:{hour} be the String representation of HourFromTime ( id:{tv} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  3:??? "Let id:{minute} be the String representation of MinFromTime ( id:{tv} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  4:??? "Let id:{second} be the String representation of SecFromTime ( id:{tv} ) , formatted as a two - digit decimal number , padded to the left with the code unit 0x0030 ( DIGIT ZERO ) if necessary ."
  |  5:return (+ (+ (+ (+ (+ (+ hour ":") minute) ":") second) " ") "GMT")
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: Type(_tv_) is Number.""",
    """            1. Assert: _tv_ is not *NaN*.""",
    """            1. Let _hour_ be the String representation of HourFromTime(_tv_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Let _minute_ be the String representation of MinFromTime(_tv_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Let _second_ be the String representation of SecFromTime(_tv_), formatted as a two-digit decimal number, padded to the left with the code unit 0x0030 (DIGIT ZERO) if necessary.""",
    """            1. Return the string-concatenation of _hour_, *":"*, _minute_, *":"*, _second_, the code unit 0x0020 (SPACE), and *"GMT"*.""",
  )
}
