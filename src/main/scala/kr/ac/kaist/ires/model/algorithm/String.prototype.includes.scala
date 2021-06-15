package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::String.prototype.includes` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.includes"""), List(Param("searchString", Normal), Param("position", Optional)))
  val ids = List(
    "sec-string.prototype.includes",
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
  |  3:if (= isRegExp true) throw TypeError else 6:{}
  |  4:app __x3__ = (ToString searchString)
  |  4:let searchStr = [? __x3__]
  |  5:app __x4__ = (ToIntegerOrInfinity position)
  |  5:let pos = [? __x4__]
  |  7:let len = S.length
  |  8:??? "Let id:{start} be the result of clamping id:{pos} between 0 and id:{len} ."
  |  9:app __x5__ = (StringIndexOf S searchStr start)
  |  9:let index = [! __x5__]
  |  10:if (! (= index -1i)) return true else 6:{}
  |  11:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _isRegExp_ be ? IsRegExp(_searchString_).""",
    """          1. If _isRegExp_ is *true*, throw a *TypeError* exception.""",
    """          1. Let _searchStr_ be ? ToString(_searchString_).""",
    """          1. Let _pos_ be ? ToIntegerOrInfinity(_position_).""",
    """          1. Assert: If _position_ is *undefined*, then _pos_ is 0.""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. Let _start_ be the result of clamping _pos_ between 0 and _len_.""",
    """          1. Let _index_ be ! StringIndexOf(_S_, _searchStr_, _start_).""",
    """          1. If _index_ is not -1, return *true*.""",
    """          1. Return *false*.""",
  )
}
