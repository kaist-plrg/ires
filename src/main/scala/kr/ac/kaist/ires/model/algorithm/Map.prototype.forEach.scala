package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Map.prototype.forEach` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.forEach"""), List(Param("callbackfn", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-map.prototype.foreach",
    "sec-properties-of-the-map-prototype-object",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (RequireInternalSlot M "MapData")
  |  1:[? __x0__]
  |  2:app __x1__ = (IsCallable callbackfn)
  |  2:if (= __x1__ false) throw TypeError else 1:{}
  |  3:let entries = M.MapData
  |  4:let __x2__ = entries
  |  4:let __x3__ = 0i
  |  4:while (< __x3__ __x2__.length) {
  |    let e = __x2__[__x3__]
  |    5:if (! (= e.Key CONST_empty)) {
  |      6:app __x4__ = (Call callbackfn thisArg (new [e.Value, e.Key, M]))
  |      6:[? __x4__]
  |    } else 1:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  7:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. If IsCallable(_callbackfn_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. For each Record { [[Key]], [[Value]] } _e_ of _entries_, do""",
    """            1. If _e_.[[Key]] is not ~empty~, then""",
    """              1. Perform ? Call(_callbackfn_, _thisArg_, « _e_.[[Value]], _e_.[[Key]], _M_ »).""",
    """          1. Return *undefined*.""",
  )
}
