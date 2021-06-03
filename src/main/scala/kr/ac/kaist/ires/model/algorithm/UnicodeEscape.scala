package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UnicodeEscape` extends Algo {
  val head = NormalHead("UnicodeEscape", List(Param("C", Normal)))
  val ids = List(
    "sec-unicodeescape",
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:??? "Let id:{n} be the numeric value of id:{C} ."
  |  1:assert (! (< 65535i n))
  |  2:return (+ (+ "\\" "u") ??? "StringOp")
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _n_ be the numeric value of _C_.""",
    """          1. Assert: _n_ ≤ 0xFFFF.""",
    """          1. Return the string-concatenation of:""",
    """            * the code unit 0x005C (REVERSE SOLIDUS)""",
    """            * *"u"*""",
    """            * the String representation of _n_, formatted as a four-digit lowercase hexadecimal number, padded to the left with zeroes if necessary""",
  )
}
