package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.pop` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.pop"""), List())
  val ids = List(
    "sec-array.prototype.pop",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  5:if (== len 0i) {
  |    3:app __x2__ = (Set O "length" 0i true)
  |    3:[? __x2__]
  |    4:return undefined
  |  } else {
  |    6:assert (< 0i len)
  |    7:let newLen = (- len 1i)
  |    8:app __x3__ = (ToString newLen)
  |    8:let index = [! __x3__]
  |    9:app __x4__ = (Get O index)
  |    9:let element = [? __x4__]
  |    10:app __x5__ = (DeletePropertyOrThrow O index)
  |    10:[? __x5__]
  |    11:app __x6__ = (Set O "length" newLen true)
  |    11:[? __x6__]
  |    12:return element
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If _len_ = 0, then""",
    """            1. Perform ? Set(_O_, *"length"*, *+0*<sub>𝔽</sub>, *true*).""",
    """            1. Return *undefined*.""",
    """          1. Else,""",
    """            1. Assert: _len_ > 0.""",
    """            1. Let _newLen_ be 𝔽(_len_ - 1).""",
    """            1. Let _index_ be ! ToString(_newLen_).""",
    """            1. Let _element_ be ? Get(_O_, _index_).""",
    """            1. Perform ? DeletePropertyOrThrow(_O_, _index_).""",
    """            1. Perform ? Set(_O_, *"length"*, _newLen_, *true*).""",
    """            1. Return _element_.""",
  )
}
