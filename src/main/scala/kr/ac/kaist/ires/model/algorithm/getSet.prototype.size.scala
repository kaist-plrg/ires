package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getSet.prototype.size` extends Algo {
  val head = BuiltinHead(parseRef("""getSet.prototype.size"""), List())
  val ids = List(
    "sec-get-set.prototype.size",
    "sec-properties-of-the-set-prototype-object",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (RequireInternalSlot S "SetData")
  |  1:[? __x0__]
  |  2:let entries = S.SetData
  |  3:let count = 0i
  |  4:let __x1__ = entries
  |  4:let __x2__ = 0i
  |  4:while (< __x2__ __x1__.length) {
  |    let e = __x1__[__x2__]
  |    5:if (! (= e CONST_empty)) count = (+ count 1i) else 16:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:return count
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_S_, [[SetData]]).""",
    """          1. Let _entries_ be the List that is _S_.[[SetData]].""",
    """          1. Let _count_ be 0.""",
    """          1. For each element _e_ of _entries_, do""",
    """            1. If _e_ is not ~empty~, set _count_ to _count_ + 1.""",
    """          1. Return 𝔽(_count_).""",
  )
}
