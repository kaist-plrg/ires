package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Map.prototype.get` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.get"""), List(Param("key", Normal)))
  val ids = List(
    "sec-map.prototype.get",
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
  |    4:if __x3__ return p.Value else 1:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  5:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~ and SameValueZero(_p_.[[Key]], _key_) is *true*, return _p_.[[Value]].""",
    """          1. Return *undefined*.""",
  )
}
