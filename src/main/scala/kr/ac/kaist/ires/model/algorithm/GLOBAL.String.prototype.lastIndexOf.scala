package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.String.prototype.lastIndexOf` extends Algo {
  val head = BuiltinHead(parseRef("""String.prototype.lastIndexOf"""), List(Param("searchString", Normal), Param("position", Optional)))
  val ids = List(
    "sec-string.prototype.lastindexof",
    "sec-properties-of-the-string-prototype-object",
    "sec-string-objects",
    "sec-text-processing",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (ToString O)
  |  1:let S = [? __x1__]
  |  2:app __x2__ = (ToString searchString)
  |  2:let searchStr = [? __x2__]
  |  3:app __x3__ = (ToNumber position)
  |  3:let numPos = [? __x3__]
  |  5:if (= numPos NaN) let pos = Infinity else {
  |    app __x4__ = (ToIntegerOrInfinity numPos)
  |    let pos = [! __x4__]
  |  }
  |  6:let len = S.length
  |  7:??? "Let id:{start} be the result of clamping id:{pos} between 0 and id:{len} ."
  |  8:let searchLen = searchStr.length
  |  9:??? "Let id:{k} be the largest possible non - negative integer not larger than id:{start} such that id:{k} + id:{searchLen} ≤ id:{len} , and for all non - negative integers id:{j} such that id:{j} < id:{searchLen} , the code unit at index id:{k} + id:{j} within id:{S} is the same as the code unit at index id:{j} within id:{searchStr} ; but if there is no such integer , let id:{k} be - 1 ."
  |  10:return k
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? RequireObjectCoercible(*this* value).""",
    """          1. Let _S_ be ? ToString(_O_).""",
    """          1. Let _searchStr_ be ? ToString(_searchString_).""",
    """          1. Let _numPos_ be ? ToNumber(_position_).""",
    """          1. Assert: If _position_ is *undefined*, then _numPos_ is *NaN*.""",
    """          1. If _numPos_ is *NaN*, let _pos_ be +∞; otherwise, let _pos_ be ! ToIntegerOrInfinity(_numPos_).""",
    """          1. Let _len_ be the length of _S_.""",
    """          1. Let _start_ be the result of clamping _pos_ between 0 and _len_.""",
    """          1. Let _searchLen_ be the length of _searchStr_.""",
    """          1. Let _k_ be the largest possible non-negative integer not larger than _start_ such that _k_ + _searchLen_ ≤ _len_, and for all non-negative integers _j_ such that _j_ < _searchLen_, the code unit at index _k_ + _j_ within _S_ is the same as the code unit at index _j_ within _searchStr_; but if there is no such integer, let _k_ be -1.""",
    """          1. Return 𝔽(_k_).""",
  )
}
