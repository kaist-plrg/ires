package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StringIndexOf` extends Algo {
  val head = NormalHead("StringIndexOf", List(Param("string", Normal), Param("searchValue", Normal), Param("fromIndex", Normal)))
  val ids = List(
    "sec-stringindexof",
    "sec-ecmascript-language-types-string-type",
    "sec-ecmascript-language-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (= (typeof string) String)
  |  1:assert (= (typeof searchValue) String)
  |  3:let len = string.length
  |  4:if (&& (= searchValue "") (! (< len fromIndex))) return fromIndex else 2:{}
  |  5:let searchLen = searchValue.length
  |  6:let i = (+ fromIndex 0i)
  |  6:let __x0__ = (+ (- len searchLen) 1i)
  |  6:while (< i __x0__) {
  |    let __x1__ = ""
  |    let __x2__ = i
  |    while (< __x2__ (+ (+ i searchLen) 1i)) {
  |      access __x3__ = (string __x2__)
  |      __x1__ = (+ __x1__ __x3__)
  |      __x2__ = (+ __x2__ 1i)
  |    }
  |    let candidate = __x1__
  |    if (= candidate searchValue) return i else {}
  |  }
  |  9:return -1i
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: Type(_string_) is String.""",
    """          1. Assert: Type(_searchValue_) is String.""",
    """          1. Assert: _fromIndex_ is a non-negative integer.""",
    """          1. Let _len_ be the length of _string_.""",
    """          1. If _searchValue_ is the empty String and _fromIndex_ ≤ _len_, return _fromIndex_.""",
    """          1. Let _searchLen_ be the length of _searchValue_.""",
    """          1. For each integer _i_ starting with _fromIndex_ such that _i_ ≤ _len_ - _searchLen_, in ascending order, do""",
    """            1. Let _candidate_ be the substring of _string_ from _i_ to _i_ + _searchLen_.""",
    """            1. If _candidate_ is the same sequence of code units as _searchValue_, return _i_.""",
    """          1. Return -1.""",
  )
}
