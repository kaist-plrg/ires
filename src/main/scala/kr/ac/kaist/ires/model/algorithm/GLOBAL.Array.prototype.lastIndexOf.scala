package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.lastIndexOf` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.lastIndexOf"""), List(Param("searchElement", Normal), Param("fromIndex", Optional)))
  val ids = List(
    "sec-array.prototype.lastindexof",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:if (= len 0i) return -1i else 4:{}
  |  3:if (! (= fromIndex absent)) {
  |    app __x2__ = (ToIntegerOrInfinity fromIndex)
  |    let n = [? __x2__]
  |  } else let n = (- len 1i)
  |  4:if (= n -Infinity) return -1i else 4:{}
  |  7:if (! (< n 0i)) {
  |    6:app __x3__ = (min n (- len 1i))
  |    6:let k = __x3__
  |  } else let k = (+ len n)
  |  9:while (! (< k 0i)) {
  |    10:app __x4__ = (ToString k)
  |    10:app __x5__ = (HasProperty O [! __x4__])
  |    10:let kPresent = [? __x5__]
  |    11:if (= kPresent true) {
  |      12:app __x6__ = (ToString k)
  |      12:app __x7__ = (Get O [! __x6__])
  |      12:let elementK = [? __x7__]
  |      13:app __x8__ = (StrictEqualityComparison searchElement elementK)
  |      13:let same = __x8__
  |      14:if (= same true) return k else 4:{}
  |    } else 4:{}
  |    15:k = (- k 1i)
  |  }
  |  16:return -1i
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If _len_ is 0, return *-1*<sub>𝔽</sub>.""",
    """          1. If _fromIndex_ is present, let _n_ be ? ToIntegerOrInfinity(_fromIndex_); else let _n_ be _len_ - 1.""",
    """          1. If _n_ is -∞, return *-1*<sub>𝔽</sub>.""",
    """          1. If _n_ ≥ 0, then""",
    """            1. Let _k_ be min(_n_, _len_ - 1).""",
    """          1. Else,""",
    """            1. Let _k_ be _len_ + _n_.""",
    """          1. Repeat, while _k_ ≥ 0,""",
    """            1. Let _kPresent_ be ? HasProperty(_O_, ! ToString(𝔽(_k_))).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _elementK_ be ? Get(_O_, ! ToString(𝔽(_k_))).""",
    """              1. Let _same_ be the result of performing Strict Equality Comparison _searchElement_ === _elementK_.""",
    """              1. If _same_ is *true*, return 𝔽(_k_).""",
    """            1. Set _k_ to _k_ - 1.""",
    """          1. Return *-1*<sub>𝔽</sub>.""",
  )
}
