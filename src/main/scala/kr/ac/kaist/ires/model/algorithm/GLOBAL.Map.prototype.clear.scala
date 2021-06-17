package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Map.prototype.clear` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.clear"""), List())
  val ids = List(
    "sec-map.prototype.clear",
    "sec-properties-of-the-map-prototype-object",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (RequireInternalSlot M "MapData")
  |  1:[? __x0__]
  |  2:let entries = M.MapData
  |  3:let __x1__ = entries
  |  3:let __x2__ = 0i
  |  3:while (< __x2__ __x1__.length) {
  |    let p = __x1__[__x2__]
  |    4:p.Key = CONST_empty
  |    5:p.Value = CONST_empty
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. Set _p_.[[Key]] to ~empty~.""",
    """            1. Set _p_.[[Value]] to ~empty~.""",
    """          1. Return *undefined*.""",
  )
}
