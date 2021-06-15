package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.prototype.sort` extends Algo {
  val head = BuiltinHead(parseRef("""Array.prototype.sort"""), List(Param("comparefn", Normal)))
  val ids = List(
    "sec-array.prototype.sort",
    "sec-properties-of-the-array-prototype-object",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:__x0__ = (! (= comparefn undefined))
  |  0:if __x0__ {
  |    app __x1__ = (IsCallable comparefn)
  |    __x0__ = (= __x1__ false)
  |  } else 25:{}
  |  0:if __x0__ throw TypeError else 25:{}
  |  1:app __x2__ = (ToObject this)
  |  1:let obj = [? __x2__]
  |  2:app __x3__ = (LengthOfArrayLike obj)
  |  2:let len = [? __x3__]
  |  3:let items = (new [])
  |  4:let k = 0i
  |  5:while (< k len) {
  |    6:app __x4__ = (ToString k)
  |    6:let Pk = [! __x4__]
  |    7:app __x5__ = (HasProperty obj Pk)
  |    7:let kPresent = [? __x5__]
  |    8:if (= kPresent true) {
  |      9:app __x6__ = (Get obj Pk)
  |      9:let kValue = [? __x6__]
  |      10:append kValue -> items
  |    } else 25:{}
  |    11:k = (+ k 1i)
  |  }
  |  12:let itemCount = items.length
  |  13:??? "Sort id:{items} using an implementation - defined sequence of calls to SortCompare . If any such call returns an abrupt completion , stop before performing any further calls to SortCompare or steps in this algorithm and return that completion ."
  |  14:let j = 0i
  |  15:while (< j itemCount) {
  |    16:app __x7__ = (ToString j)
  |    16:app __x8__ = (Set obj [! __x7__] items[j] true)
  |    16:[? __x8__]
  |    17:j = (+ j 1i)
  |  }
  |  18:while (< j len) {
  |    19:app __x9__ = (ToString j)
  |    19:app __x10__ = (DeletePropertyOrThrow obj [! __x9__])
  |    19:[? __x10__]
  |    20:j = (+ j 1i)
  |  }
  |  21:return obj
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. [id="step-array-sort-comparefn"] If _comparefn_ is not *undefined* and IsCallable(_comparefn_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _obj_ be ? ToObject(*this* value).""",
    """          1. [id="step-array-sort-len"] Let _len_ be ? LengthOfArrayLike(_obj_).""",
    """          1. Let _items_ be a new empty List.""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Let _kPresent_ be ? HasProperty(_obj_, _Pk_).""",
    """            1. If _kPresent_ is *true*, then""",
    """              1. Let _kValue_ be ? Get(_obj_, _Pk_).""",
    """              1. Append _kValue_ to _items_.""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Let _itemCount_ be the number of elements in _items_.""",
    """          1. [id="step-array-sort"] Sort _items_ using an implementation-defined sequence of calls to SortCompare. If any such call returns an abrupt completion, stop before performing any further calls to SortCompare or steps in this algorithm and return that completion.""",
    """          1. Let _j_ be 0.""",
    """          1. Repeat, while _j_ < _itemCount_,""",
    """            1. Perform ? Set(_obj_, ! ToString(𝔽(_j_)), _items_[_j_], *true*).""",
    """            1. Set _j_ to _j_ + 1.""",
    """          1. Repeat, while _j_ < _len_,""",
    """            1. Perform ? DeletePropertyOrThrow(_obj_, ! ToString(𝔽(_j_))).""",
    """            1. Set _j_ to _j_ + 1.""",
    """          1. Return _obj_.""",
  )
}
