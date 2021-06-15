package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeTypedArrayFromArrayLike` extends Algo {
  val head = NormalHead("InitializeTypedArrayFromArrayLike", List(Param("O", Normal), Param("arrayLike", Normal)))
  val ids = List(
    "sec-initializetypedarrayfromarraylike",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (LengthOfArrayLike arrayLike)
  |  1:let len = [? __x0__]
  |  2:app __x1__ = (AllocateTypedArrayBuffer O len)
  |  2:[? __x1__]
  |  3:let k = 0i
  |  4:while (< k len) {
  |    5:app __x2__ = (ToString k)
  |    5:let Pk = [! __x2__]
  |    6:app __x3__ = (Get arrayLike Pk)
  |    6:let kValue = [? __x3__]
  |    7:app __x4__ = (Set O Pk kValue true)
  |    7:[? __x4__]
  |    8:k = (+ k 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _O_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Let _len_ be ? LengthOfArrayLike(_arrayLike_).""",
    """            1. Perform ? AllocateTypedArrayBuffer(_O_, _len_).""",
    """            1. Let _k_ be 0.""",
    """            1. Repeat, while _k_ < _len_,""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Let _kValue_ be ? Get(_arrayLike_, _Pk_).""",
    """              1. Perform ? Set(_O_, _Pk_, _kValue_, *true*).""",
    """              1. Set _k_ to _k_ + 1.""",
  )
}
