package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.push` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.push"""), List(Param("items", Variadic)))
  val ids = List(
    "sec-array.prototype.push",
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
  |  3:if (< (- (** 2.0 53i) 1i) (+ len argCount)) throw TypeError else 4:{}
  |  4:let __x2__ = items
  |  4:let __x3__ = 0i
  |  4:while (< __x3__ __x2__.length) {
  |    let E = __x2__[__x3__]
  |    5:app __x4__ = (ToString len)
  |    5:app __x5__ = (Set O [! __x4__] E true)
  |    5:[? __x5__]
  |    6:len = (+ len 1i)
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  7:app __x6__ = (Set O "length" len true)
  |  7:[? __x6__]
  |  8:return len
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be ? ToObject(*this* value).""",
    """          1. Let _len_ be ? LengthOfArrayLike(_O_).""",
    """          1. Let _argCount_ be the number of elements in _items_.""",
    """          1. If _len_ + _argCount_ > 2<sup>53</sup> - 1, throw a *TypeError* exception.""",
    """          1. For each element _E_ of _items_, do""",
    """            1. Perform ? Set(_O_, ! ToString(𝔽(_len_)), _E_, *true*).""",
    """            1. Set _len_ to _len_ + 1.""",
    """          1. Perform ? Set(_O_, *"length"*, 𝔽(_len_), *true*).""",
    """          1. Return 𝔽(_len_).""",
  )
}
