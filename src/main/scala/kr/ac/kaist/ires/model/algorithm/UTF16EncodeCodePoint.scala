package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UTF16EncodeCodePoint` extends Algo {
  val head = NormalHead("UTF16EncodeCodePoint", List(Param("cp", Normal)))
  val ids = List(
    "sec-utf16encodecodepoint",
    "sec-source-text",
    "sec-ecmascript-language-source-code",
  )
  val rawBody = parseInst("""{
  |  0:assert (&& (! (< cp 0i)) (! (< 1114111i cp)))
  |  1:if (! (< 65535i cp)) return cp else 3:{}
  |  2:app __x0__ = (floor (/ (- cp 65536i) 1024i))
  |  2:let cu1 = (+ __x0__ 55296i)
  |  3:let cu2 = (+ (%% (- cp 65536i) 1024i) 56320i)
  |  4:return (+ cu1 cu2)
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: 0 ≤ _cp_ ≤ 0x10FFFF.""",
    """        1. If _cp_ ≤ 0xFFFF, return the String value consisting of the code unit whose value is _cp_.""",
    """        1. Let _cu1_ be the code unit whose value is floor((_cp_ - 0x10000) / 0x400) + 0xD800.""",
    """        1. Let _cu2_ be the code unit whose value is ((_cp_ - 0x10000) modulo 0x400) + 0xDC00.""",
    """        1. Return the string-concatenation of _cu1_ and _cu2_.""",
  )
}
