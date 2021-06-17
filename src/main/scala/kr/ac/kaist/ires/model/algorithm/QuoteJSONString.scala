package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::QuoteJSONString` extends Algo {
  val head = NormalHead("QuoteJSONString", List(Param("value", Normal)))
  val ids = List(
    "sec-quotejsonstring",
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let product = "\""
  |  1:app __x0__ = (StringToCodePoints value)
  |  1:let __x1__ = [! __x0__]
  |  1:let __x2__ = 0i
  |  1:while (< __x2__ __x1__.length) {
  |    let C = __x1__[__x2__]
  |    2:??? "If id:{C} is listed in the “ Code Point ” column of link:{table-json-single-character-escapes} , then in:{} out:{}"
  |    4:??? "Else if id:{C} has a numeric value less than 0x0020 ( SPACE ) , or if id:{C} has the same numeric value as a link:{leading-surrogate} or link:{trailing-surrogate} , then in:{} out:{}"
  |    7:??? "Else , in:{} out:{}"
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  9:product = (+ product "\"")
  |  10:return product
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _product_ be the String value consisting solely of the code unit 0x0022 (QUOTATION MARK).""",
    """          1. For each code point _C_ of ! StringToCodePoints(_value_), do""",
    """            1. If _C_ is listed in the “Code Point” column of <emu-xref href="#table-json-single-character-escapes"></emu-xref>, then""",
    """              1. Set _product_ to the string-concatenation of _product_ and the escape sequence for _C_ as specified in the “Escape Sequence” column of the corresponding row.""",
    """            1. Else if _C_ has a numeric value less than 0x0020 (SPACE), or if _C_ has the same numeric value as a <emu-xref href="#leading-surrogate"></emu-xref> or <emu-xref href="#trailing-surrogate"></emu-xref>, then""",
    """              1. Let _unit_ be the code unit whose numeric value is that of _C_.""",
    """              1. Set _product_ to the string-concatenation of _product_ and UnicodeEscape(_unit_).""",
    """            1. Else,""",
    """              1. Set _product_ to the string-concatenation of _product_ and ! UTF16EncodeCodePoint(_C_).""",
    """          1. Set _product_ to the string-concatenation of _product_ and the code unit 0x0022 (QUOTATION MARK).""",
    """          1. Return _product_.""",
  )
}
