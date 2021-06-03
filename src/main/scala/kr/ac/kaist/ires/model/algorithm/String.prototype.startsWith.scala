package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.startsWith` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.startsWith"""), List(Param("searchString", Normal), Param("position", Optional)))
  val ids = List(
    "sec-string.prototype.startswith",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (IsRegExp searchString)
  |  2:let isRegExp = [? __x2__]
  |  3:if (= isRegExp true) throw TypeError else 25:{}
  |  4:app __x3__ = (ToString searchString)
  |  4:let searchStr = [? __x3__]
  |  5:let len = S.length
  |  6:if (= position undefined) let pos = 0i else {
  |    app __x4__ = (ToIntegerOrInfinity position)
  |    let pos = [? __x4__]
  |  }
  |  7:??? "Let id:{start} be the result of clamping id:{pos} between 0 and id:{len} ."
  |  8:let searchLength = searchStr.length
  |  9:if (== searchLength 0i) return true else 25:{}
  |  10:let end = (+ start searchLength)
  |  11:if (< len end) return false else 25:{}
  |  12:let __x5__ = ""
  |  12:let __x6__ = start
  |  12:while (< __x6__ (+ end 1i)) {
  |    access __x7__ = (S __x6__)
  |    __x5__ = (+ __x5__ __x7__)
  |    __x6__ = (+ __x6__ 1i)
  |  }
  |  12:let substring = __x5__
  |  13:app __x8__ = (SameValueNonNumeric substring searchStr)
  |  13:return [! __x8__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _isRegExp_ be ? IsRegExp(_searchString_).""",
    """          1. If _isRegExp_ is *true*, throw a *TypeError* exception.""",
    """          1. Let _searchStr_ be ? ToString(_searchString_).""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. If _position_ is *undefined*, let _pos_ be 0; else let _pos_ be ? ToIntegerOrInfinity(_position_).""",
    """          1. Let _start_ be the result of clamping _pos_ between 0 and _len_.""",
    """          1. Let _searchLength_ be the length of _searchStr_.""",
    """          1. If _searchLength_ = 0, return *true*.""",
    """          1. Let _end_ be _start_ + _searchLength_.""",
    """          1. If _end_ > _len_, return *false*.""",
    """          1. Let _substring_ be the substring of _S_ from _start_ to _end_.""",
    """          1. Return ! SameValueNonNumeric(_substring_, _searchStr_).""",
  )
}
