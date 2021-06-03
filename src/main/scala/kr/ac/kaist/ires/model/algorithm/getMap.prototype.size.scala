package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getMap.prototype.size` extends Algo {
  val head = BuiltinHead(parseRef("""getMap.prototype.size"""), List())
  val ids = List(
    "sec-get-map.prototype.size",
    "sec-properties-of-the-map-prototype-object",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (RequireInternalSlot M "MapData")
  |  1:[? __x0__]
  |  2:let entries = M.MapData
  |  3:let count = 0i
  |  4:let __x1__ = entries
  |  4:let __x2__ = 0i
  |  4:while (< __x2__ __x1__.length) {
  |    let p = __x1__[__x2__]
  |    5:if (! (= p.Key CONST_empty)) count = (+ count 1i) else 1:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:return count
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. Let _count_ be 0.""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~, set _count_ to _count_ + 1.""",
    """          1. Return 𝔽(_count_).""",
  )
}
