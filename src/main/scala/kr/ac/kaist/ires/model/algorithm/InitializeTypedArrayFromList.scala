package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeTypedArrayFromList` extends Algo {
  val head = NormalHead("InitializeTypedArrayFromList", List(Param("O", Normal), Param("values", Normal)))
  val ids = List(
    "sec-initializetypedarrayfromlist",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:let len = values.length
  |  2:app __x0__ = (AllocateTypedArrayBuffer O len)
  |  2:[? __x0__]
  |  3:let k = 0i
  |  4:while (< k len) {
  |    5:app __x1__ = (ToString k)
  |    5:let Pk = [! __x1__]
  |    6:let kValue = (pop values 0i)
  |    7:app __x2__ = (Set O Pk kValue true)
  |    7:[? __x2__]
  |    8:k = (+ k 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _O_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Let _len_ be the number of elements in _values_.""",
    """            1. Perform ? AllocateTypedArrayBuffer(_O_, _len_).""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat, while _k_ < _len_,""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Let _kValue_ be the first element of _values_ and remove that element from _values_.""",
    """              1. Perform ? Set(_O_, _Pk_, _kValue_, *true*).""",
    """              1. Set _k_ to _k_ + 1.""",
    """            1. Assert: _values_ is now an empty List.""",
  )
}
