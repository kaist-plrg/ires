package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Array.prototype.unshift` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.unshift"""), List(Param("items", Variadic)))
  val ids = List(
    "sec-array.prototype.unshift",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToObject this)
  |  0:let O = [? __x0__]
  |  1:app __x1__ = (LengthOfArrayLike O)
  |  1:let len = [? __x1__]
  |  2:let argCount = items.length
  |  3:if (< 0i argCount) {
  |    4:if (< (- (** 2.0 53i) 1i) (+ len argCount)) throw TypeError else 25:{}
  |    5:let k = len
  |    6:while (< 0i k) {
  |      7:app __x2__ = (ToString (- k 1i))
  |      7:let from = [! __x2__]
  |      8:app __x3__ = (ToString (- (+ k argCount) 1i))
  |      8:let to = [! __x3__]
  |      9:app __x4__ = (HasProperty O from)
  |      9:let fromPresent = [? __x4__]
  |      13:if (= fromPresent true) {
  |        11:app __x5__ = (Get O from)
  |        11:let fromValue = [? __x5__]
  |        12:app __x6__ = (Set O to fromValue true)
  |        12:[? __x6__]
  |      } else {
  |        14:assert (= fromPresent false)
  |        15:app __x7__ = (DeletePropertyOrThrow O to)
  |        15:[? __x7__]
  |      }
  |      16:k = (- k 1i)
  |    }
  |    17:let j = 0i
  |    18:let __x8__ = items
  |    18:let __x9__ = 0i
  |    18:while (< __x9__ __x8__.length) {
  |      let E = __x8__[__x9__]
  |      19:app __x10__ = (ToString j)
  |      19:app __x11__ = (Set O [! __x10__] E true)
  |      19:[? __x11__]
  |      20:j = (+ j 1i)
  |      __x9__ = (+ __x9__ 1i)
  |    }
  |  } else 25:{}
  |  21:app __x12__ = (Set O "length" (+ len argCount) true)
  |  21:[? __x12__]
  |  22:return (+ len argCount)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _argCount_ be the number of elements in _items_.""",
    """          1. If _argCount_ > 0, then""",
    """            1. If _len_ + _argCount_ > 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """            1. Let _k_ be _len_.""",
    """            1. Repeat, while _k_ > 0,""",
    """              1. Let _from_ be ! ToString(𝔽(_k_ - 1)).""",
    """              1. Let _to_ be ! ToString(𝔽(_k_ + _argCount_ - 1)).""",
    """              1. Let _fromPresent_ be ? HasProperty(_O_, _from_).""",
    """              1. If _fromPresent_ is *true*, then""",
    """                1. Let _fromValue_ be ? Get(_O_, _from_).""",
    """                1. Perform ? Set(_O_, _to_, _fromValue_, *true*).""",
    """              1. Else,""",
    """                1. Assert: _fromPresent_ is *false*.""",
    """                1. Perform ? DeletePropertyOrThrow(_O_, _to_).""",
    """              1. Set _k_ to _k_ - 1.""",
    """            1. Let _j_ be *+0*<sub>𝔽</sub>.""",
    """            1. For each element _E_ of _items_, do""",
    """              1. Perform ? Set(_O_, ! ToString(_j_), _E_, *true*).""",
    """              1. Set _j_ to _j_ + *1*<sub>𝔽</sub>.""",
    """          1. Perform ? Set(_O_, *"length"*, 𝔽(_len_ + _argCount_), *true*).""",
    """          1. Return 𝔽(_len_ + _argCount_).""",
  )
}
