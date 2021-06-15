package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToIndex` extends Algo {
  val head = NormalHead("ToIndex", List(Param("value", Normal)))
  val ids = List(
    "sec-toindex",
    "sec-type-conversion",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""if (= value undefined) return 0i else {
  |  3:app __x0__ = (ToIntegerOrInfinity value)
  |  3:let integerIndex = [? __x0__]
  |  4:if (< integerIndex 0i) throw RangeError else 0:{}
  |  5:app __x1__ = (ToLength integerIndex)
  |  5:let index = [! __x1__]
  |  6:app __x2__ = (SameValue integerIndex index)
  |  6:if (= [! __x2__] false) throw RangeError else 0:{}
  |  7:return index
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _value_ is *undefined*, then""",
    """          1. Return 0.""",
    """        1. Else,""",
    """          1. Let _integerIndex_ be 𝔽(? ToIntegerOrInfinity(_value_)).""",
    """          1. If _integerIndex_ < *+0*<sub>𝔽</sub>, throw a *RangeError* exception.""",
    """          1. Let _index_ be ! ToLength(_integerIndex_).""",
    """          1. If ! SameValue(_integerIndex_, _index_) is *false*, throw a *RangeError* exception.""",
    """          1. Return ℝ(_index_).""",
  )
}
