package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringToCodePoints` extends Algo {
  val head = NormalHead("StringToCodePoints", List(Param("string", Normal)))
  val ids = List(
    "sec-stringtocodepoints",
    "sec-source-text",
    "sec-ecmascript-language-source-code",
  )
  val rawBody = parseInst("""{
  |  0:let codePoints = (new [])
  |  1:let size = string.length
  |  2:let position = 0i
  |  3:while (< position size) {
  |    4:app __x0__ = (CodePointAt string position)
  |    4:let cp = [! __x0__]
  |    5:append cp.CodePoint -> codePoints
  |    6:position = (+ position cp.CodeUnitCount)
  |  }
  |  7:return codePoints
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _codePoints_ be a new empty List.""",
    """        1. Let _size_ be the length of _string_.""",
    """        1. Let _position_ be 0.""",
    """        1. Repeat, while _position_ < _size_,""",
    """          1. Let _cp_ be ! CodePointAt(_string_, _position_).""",
    """          1. Append _cp_.[[CodePoint]] to _codePoints_.""",
    """          1. Set _position_ to _position_ + _cp_.[[CodeUnitCount]].""",
    """        1. Return _codePoints_.""",
  )
}
