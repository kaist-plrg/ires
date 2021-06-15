package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Array.of` extends Algo {
  val head = BuiltinHead(parseRef("""Array.of"""), List(Param("items", Variadic)))
  val ids = List(
    "sec-array.of",
    "sec-properties-of-the-array-constructor",
    "sec-array-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let len = items.length
  |  1:let lenNumber = len
  |  2:let C = this
  |  5:app __x0__ = (IsConstructor C)
  |  5:if (= __x0__ true) {
  |    4:app __x1__ = (Construct C (new [lenNumber]))
  |    4:let A = [? __x1__]
  |  } else {
  |    6:app __x2__ = (ArrayCreate len)
  |    6:let A = [? __x2__]
  |  }
  |  7:let k = 0i
  |  8:while (< k len) {
  |    9:let kValue = items[k]
  |    10:app __x3__ = (ToString k)
  |    10:let Pk = [! __x3__]
  |    11:app __x4__ = (CreateDataPropertyOrThrow A Pk kValue)
  |    11:[? __x4__]
  |    12:k = (+ k 1i)
  |  }
  |  13:app __x5__ = (Set A "length" lenNumber true)
  |  13:[? __x5__]
  |  14:return A
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _len_ be the number of elements in _items_.""",
    """          1. Let _lenNumber_ be 𝔽(_len_).""",
    """          1. Let _C_ be the *this* value.""",
    """          1. If IsConstructor(_C_) is *true*, then""",
    """            1. Let _A_ be ? Construct(_C_, « _lenNumber_ »).""",
    """          1. Else,""",
    """            1. Let _A_ be ? ArrayCreate(_len_).""",
    """          1. Let _k_ be 0.""",
    """          1. Repeat, while _k_ < _len_,""",
    """            1. Let _kValue_ be _items_[_k_].""",
    """            1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """            1. Perform ? CreateDataPropertyOrThrow(_A_, _Pk_, _kValue_).""",
    """            1. Set _k_ to _k_ + 1.""",
    """          1. Perform ? Set(_A_, *"length"*, _lenNumber_, *true*).""",
    """          1. Return _A_.""",
  )
}
