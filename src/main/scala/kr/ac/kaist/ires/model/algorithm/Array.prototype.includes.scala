package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.includes` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.includes"""), List(Param("searchElement", Normal), Param("fromIndex", Optional)))
  val ids = List(
    "sec-array.prototype.includes",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:if (= len 0i) return false else 4:{}
  |  3:app __x2__ = (ToIntegerOrInfinity fromIndex)
  |  3:let n = [? __x2__]
  |  6:if (= n Infinity) return false else if (= n -Infinity) n = 0i else 4:{}
  |  9:if (! (< n 0i)) let k = n else {
  |    10:let k = (+ len n)
  |    11:if (< k 0i) k = 0i else 4:{}
  |  }
  |  12:while (< k len) {
  |    13:app __x3__ = (ToString k)
  |    13:app __x4__ = (Get O [! __x3__])
  |    13:let elementK = [? __x4__]
  |    14:app __x5__ = (SameValueZero searchElement elementK)
  |    14:if (= __x5__ true) return true else 4:{}
  |    15:k = (+ k 1i)
  |  }
  |  16:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If _len_ is 0, return *false*.""",
    """          1. Let _n_ be ? ToIntegerOrInfinity(_fromIndex_).""",
    """          1. Assert: If _fromIndex_ is *undefined*, then _n_ is 0.""",
    """          1. If _n_ is +∞, return *false*.""",
    """          1. Else if _n_ is -∞, set _n_ to 0.""",
    """          1. If _n_ ≥ 0, then""",
    """            1. Let _k_ be _n_.""",
    """          1. Else,""",
    """            1. Let _k_ be _len_ + _n_.""",
    """            1. If _k_ < 0, set _k_ to 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _elementK_ be ? Get(_O_, ! ToString(𝔽(_k_))).""",
    """            1. If SameValueZero(_searchElement_, _elementK_) is *true*, return *true*.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return *false*.""",
  )
}
