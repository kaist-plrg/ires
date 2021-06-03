package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UTF16SurrogatePairToCodePoint` extends Algo {
  val head = NormalHead("UTF16SurrogatePairToCodePoint", List(Param("lead", Normal), Param("trail", Normal)))
  val ids = List(
    "sec-utf16decodesurrogatepair",
    "sec-source-text",
    "sec-ecmascript-language-source-code",
  )
  val rawBody = parseInst("""{
  |  1:let cp = (+ (+ (* (- lead 55296i) 1024i) (- trail 56320i)) 65536i)
  |  2:return cp
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _lead_ is a <emu-xref href="#leading-surrogate"></emu-xref> and _trail_ is a <emu-xref href="#trailing-surrogate"></emu-xref>.""",
    """        1. Let _cp_ be (_lead_ - 0xD800) × 0x400 + (_trail_ - 0xDC00) + 0x10000.""",
    """        1. Return the code point _cp_.""",
  )
}
