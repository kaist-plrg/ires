package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.shift` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.shift"""), List())
  val ids = List(
    "sec-array.prototype.shift",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:if (== len 0i) {
  |    3:app __x2__ = (Set O "length" 0i true)
  |    3:[? __x2__]
  |    4:return undefined
  |  } else 25:{}
  |  5:app __x3__ = (Get O "0")
  |  5:let first = [? __x3__]
  |  6:let k = 1i
  |  7:while (< k len) {
  |    8:app __x4__ = (ToString k)
  |    8:let from = [! __x4__]
  |    9:app __x5__ = (ToString (- k 1i))
  |    9:let to = [! __x5__]
  |    10:app __x6__ = (HasProperty O from)
  |    10:let fromPresent = [? __x6__]
  |    14:if (= fromPresent true) {
  |      12:app __x7__ = (Get O from)
  |      12:let fromVal = [? __x7__]
  |      13:app __x8__ = (Set O to fromVal true)
  |      13:[? __x8__]
  |    } else {
  |      15:assert (= fromPresent false)
  |      16:app __x9__ = (DeletePropertyOrThrow O to)
  |      16:[? __x9__]
  |    }
  |    17:k = (+ k 1i)
  |  }
  |  18:app __x10__ = (ToString (- len 1i))
  |  18:app __x11__ = (DeletePropertyOrThrow O [! __x10__])
  |  18:[? __x11__]
  |  19:app __x12__ = (Set O "length" (- len 1i) true)
  |  19:[? __x12__]
  |  20:return first
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If _len_ = 0, then""",
    """            1. Perform ? Set(_O_, *"length"*, *+0*<sub>𝔽</sub>, *true*).""",
    """            1. Return *undefined*.""",
    """          1. Let _first_ be ? Get(_O_, *"0"*).""",
    """          1. Let _k_ be 1.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _from_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _to_ be ! ToString(𝔽(_k_ - 1)).""",
    """            1. Let _fromPresent_ be ? HasProperty(_O_, _from_).""",
    """            1. If _fromPresent_ is *true*, then""",
    """              1. Let _fromVal_ be ? Get(_O_, _from_).""",
    """              1. Perform ? Set(_O_, _to_, _fromVal_, *true*).""",
    """            1. Else,""",
    """              1. Assert: _fromPresent_ is *false*.""",
    """              1. Perform ? DeletePropertyOrThrow(_O_, _to_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Perform ? DeletePropertyOrThrow(_O_, ! ToString(𝔽(_len_ - 1))).""",
    """          1. Perform ? Set(_O_, *"length"*, 𝔽(_len_ - 1), *true*).""",
    """          1. Return _first_.""",
  )
}
