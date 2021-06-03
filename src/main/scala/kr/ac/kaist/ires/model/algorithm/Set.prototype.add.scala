package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Set.prototype.add` extends Algo {
  val head = BuiltinHead(parseRef("""Set.prototype.add"""), List(Param("value", Normal)))
  val ids = List(
    "sec-set.prototype.add",
    "sec-properties-of-the-set-prototype-object",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (RequireInternalSlot S "SetData")
  |  1:[? __x0__]
  |  2:let entries = S.SetData
  |  3:let __x1__ = entries
  |  3:let __x2__ = 0i
  |  3:while (< __x2__ __x1__.length) {
  |    let e = __x1__[__x2__]
  |    4:let __x3__ = true
  |    4:__x3__ = (! (= e CONST_empty))
  |    4:if __x3__ {
  |      app __x4__ = (SameValueZero e value)
  |      __x3__ = (= __x4__ true)
  |    } else 16:{}
  |    4:if __x3__ return S else 16:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:if (= value -0.0) value = 0i else 16:{}
  |  7:append value -> entries
  |  8:return S
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_S_, [[SetData]]).""",
    """          1. Let _entries_ be the List that is _S_.[[SetData]].""",
    """          1. For each element _e_ of _entries_, do""",
    """            1. If _e_ is not ~empty~ and SameValueZero(_e_, _value_) is *true*, then""",
    """              1. Return _S_.""",
    """          1. If _value_ is *-0*<sub>𝔽</sub>, set _value_ to *+0*<sub>𝔽</sub>.""",
    """          1. Append _value_ as the last element of _entries_.""",
    """          1. Return _S_.""",
  )
}
