package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Map.prototype.delete` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.delete"""), List(Param("key", Normal)))
  val ids = List(
    "sec-map.prototype.delete",
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
  |    4:let __x3__ = true
  |    4:__x3__ = (! (= p.Key CONST_empty))
  |    4:if __x3__ {
  |      app __x4__ = (SameValueZero p.Key key)
  |      __x3__ = (= __x4__ true)
  |    } else 1:{}
  |    4:if __x3__ {
  |      5:p.Key = CONST_empty
  |      6:p.Value = CONST_empty
  |      7:return true
  |    } else 1:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  8:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~ and SameValueZero(_p_.[[Key]], _key_) is *true*, then""",
    """              1. Set _p_.[[Key]] to ~empty~.""",
    """              1. Set _p_.[[Value]] to ~empty~.""",
    """              1. Return *true*.""",
    """          1. Return *false*.""",
  )
}
