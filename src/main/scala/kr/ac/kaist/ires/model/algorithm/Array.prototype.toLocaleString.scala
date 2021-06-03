package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.toLocaleString` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.toLocaleString"""), List(Param("reserved1", Optional), Param("reserved2", Optional)))
  val ids = List(
    "sec-array.prototype.tolocalestring",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let array = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike array)
  |  1:let len = [? __x1__]
  |  2:??? "Let id:{separator} be the String value for the list - separator String appropriate for the host environment ' s current locale ( this is derived in an implementation - defined way ) ."
  |  3:let R = ""
  |  4:let k = 0i
  |  5:while (< k len) {
  |    6:if (< 0i k) R = (+ R separator) else 25:{}
  |    8:app __x2__ = (ToString k)
  |    8:app __x3__ = (Get array [! __x2__])
  |    8:let nextElement = [? __x3__]
  |    9:if (! (|| (= nextElement undefined) (= nextElement null))) {
  |      10:app __x4__ = (Invoke nextElement "toLocaleString")
  |      10:app __x5__ = (ToString [? __x4__])
  |      10:let S = [? __x5__]
  |      11:R = (+ R S)
  |    } else 25:{}
  |    12:k = (+ k 1i)
  |  }
  |  13:return R
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _array_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_array_).""",
    """          1. Let _separator_ be the String value for the list-separator String appropriate for the host environment's current locale (this is derived in an implementation-defined way).""",
    """          1. Let _R_ be the empty String.""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. If _k_ > 0, then""",
    """              1. Set _R_ to the string-concatenation of _R_ and _separator_.""",
    """            1. Let _nextElement_ be ? Get(_array_, ! ToString(𝔽(_k_))).""",
    """            1. If _nextElement_ is not *undefined* or *null*, then""",
    """              1. Let _S_ be ? ToString(? Invoke(_nextElement_, *"toLocaleString"*)).""",
    """              1. Set _R_ to the string-concatenation of _R_ and _S_.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return _R_.""",
  )
}
