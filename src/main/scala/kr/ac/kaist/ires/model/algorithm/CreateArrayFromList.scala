package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateArrayFromList` extends Algo {
  val head = NormalHead("CreateArrayFromList", List(Param("elements", Normal)))
  val ids = List(
    "sec-createarrayfromlist",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  1:app __x0__ = (ArrayCreate 0i)
  |  1:let array = [! __x0__]
  |  2:let n = 0i
  |  3:let __x1__ = elements
  |  3:let __x2__ = 0i
  |  3:while (< __x2__ __x1__.length) {
  |    let e = __x1__[__x2__]
  |    4:app __x3__ = (ToString n)
  |    4:app __x4__ = (CreateDataPropertyOrThrow array [! __x3__] e)
  |    4:[! __x4__]
  |    5:n = (+ n 1i)
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:return array
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _elements_ is a List whose elements are all ECMAScript language values.""",
    """        1. Let _array_ be ! ArrayCreate(0).""",
    """        1. Let _n_ be 0.""",
    """        1. For each element _e_ of _elements_, do""",
    """          1. Perform ! CreateDataPropertyOrThrow(_array_, ! ToString(𝔽(_n_)), _e_).""",
    """          1. Set _n_ to _n_ + 1.""",
    """        1. Return _array_.""",
  )
}
