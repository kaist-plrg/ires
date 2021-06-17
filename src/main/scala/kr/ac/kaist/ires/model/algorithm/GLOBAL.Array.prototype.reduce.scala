package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.reduce` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.reduce"""), List(Param("callbackfn", Normal), Param("initialValue", Optional)))
  val ids = List(
    "sec-array.prototype.reduce",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:app __x2__ = (IsCallable callbackfn)
  |  2:if (= __x2__ false) throw TypeError else 4:{}
  |  3:if (&& (== len 0i) (= initialValue absent)) throw TypeError else 4:{}
  |  4:let k = 0i
  |  5:let accumulator = undefined
  |  8:if (! (= initialValue absent)) accumulator = initialValue else {
  |    9:let kPresent = false
  |    10:while (&& (= kPresent false) (< k len)) {
  |      11:app __x3__ = (ToString k)
  |      11:let Pk = [! __x3__]
  |      12:app __x4__ = (HasProperty O Pk)
  |      12:kPresent = [? __x4__]
  |      13:if (= kPresent true) {
  |        14:app __x5__ = (Get O Pk)
  |        14:accumulator = [? __x5__]
  |      } else 4:{}
  |      15:k = (+ k 1i)
  |    }
  |    16:if (= kPresent false) throw TypeError else 4:{}
  |  }
  |  17:while (< k len) {
  |    18:app __x6__ = (ToString k)
  |    18:let Pk = [! __x6__]
  |    19:app __x7__ = (HasProperty O Pk)
  |    19:let kPresent = [? __x7__]
  |    20:if (= kPresent true) {
  |      21:app __x8__ = (Get O Pk)
  |      21:let kValue = [? __x8__]
  |      22:app __x9__ = (Call callbackfn undefined (new [accumulator, kValue, k, O]))
  |      22:accumulator = [? __x9__]
  |    } else 4:{}
  |    23:k = (+ k 1i)
  |  }
  |  24:return accumulator
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. If IsCallable(_callbackfn_) is *false*, throw a *TypeError* exception.""",
    """          1. If _len_ = 0 and _initialValue_ is not present, throw a *TypeError* exception.""",
    """          1. Let _k_ be 0.""",
    """          1. Let _accumulator_ be *undefined*.""",
    """          1. If _initialValue_ is present, then""",
    """            1. Set _accumulator_ to _initialValue_.""",
    """          1. Else,""",
    """            1. Let _kPresent_ be *false*.""",
    """            1. Repeat, while _kPresent_ is *false* and _k_ < _len_,""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Set _kPresent_ to ? HasProperty(_O_, _Pk_).""",
    """              1. If _kPresent_ is *true*, then""",
    """                1. Set _accumulator_ to ? Get(_O_, _Pk_).""",
    """              1. Set _k_ to _k_ + 1.""",
    """            1. If _kPresent_ is *false*, throw a *TypeError* exception.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kPresent_ be ? HasProperty(_O_, _Pk_).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _kValue_ be ? Get(_O_, _Pk_).""",
    """              1. Set _accumulator_ to ? Call(_callbackfn_, *undefined*, « _accumulator_, _kValue_, 𝔽(_k_), _O_ »).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Return _accumulator_.""",
  )
}
