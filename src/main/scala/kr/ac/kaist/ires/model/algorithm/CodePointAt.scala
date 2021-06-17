package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CodePointAt` extends Algo {
  val head = NormalHead("CodePointAt", List(Param("string", Normal), Param("position", Normal)))
  val ids = List(
    "sec-codepointat",
    "sec-source-text",
    "sec-ecmascript-language-source-code",
  )
  val rawBody = parseInst("""{
  |  0:let size = string.length
  |  1:assert (&& (! (< position 0i)) (< position size))
  |  2:let first = string[position]
  |  3:??? "Let id:{cp} be the code point whose numeric value is that of id:{first} ."
  |  4:??? "If id:{first} is not a link:{leading-surrogate} or link:{trailing-surrogate} , then in:{} out:{}"
  |  6:??? "If id:{first} is a link:{trailing-surrogate} or id:{position} + 1 = id:{size} , then in:{} out:{}"
  |  8:let second = string[(+ position 1i)]
  |  9:??? "If id:{second} is not a link:{trailing-surrogate} , then in:{} out:{}"
  |  11:app __x0__ = (UTF16SurrogatePairToCodePoint first second)
  |  11:cp = [! __x0__]
  |  12:return (new Record("CodePoint" -> cp, "CodeUnitCount" -> 2i, "IsUnpairedSurrogate" -> false))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _size_ be the length of _string_.""",
    """        1. Assert: _position_ ≥ 0 and _position_ < _size_.""",
    """        1. Let _first_ be the code unit at index _position_ within _string_.""",
    """        1. Let _cp_ be the code point whose numeric value is that of _first_.""",
    """        1. If _first_ is not a <emu-xref href="#leading-surrogate"></emu-xref> or <emu-xref href="#trailing-surrogate"></emu-xref>, then""",
    """          1. Return the Record { [[CodePoint]]: _cp_, [[CodeUnitCount]]: 1, [[IsUnpairedSurrogate]]: *false* }.""",
    """        1. If _first_ is a <emu-xref href="#trailing-surrogate"></emu-xref> or _position_ + 1 = _size_, then""",
    """          1. Return the Record { [[CodePoint]]: _cp_, [[CodeUnitCount]]: 1, [[IsUnpairedSurrogate]]: *true* }.""",
    """        1. Let _second_ be the code unit at index _position_ + 1 within _string_.""",
    """        1. If _second_ is not a <emu-xref href="#trailing-surrogate"></emu-xref>, then""",
    """          1. Return the Record { [[CodePoint]]: _cp_, [[CodeUnitCount]]: 1, [[IsUnpairedSurrogate]]: *true* }.""",
    """        1. Set _cp_ to ! UTF16SurrogatePairToCodePoint(_first_, _second_).""",
    """        1. Return the Record { [[CodePoint]]: _cp_, [[CodeUnitCount]]: 2, [[IsUnpairedSurrogate]]: *false* }.""",
  )
}
