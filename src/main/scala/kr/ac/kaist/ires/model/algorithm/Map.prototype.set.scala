package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Map.prototype.set` extends Algo {
  val head = BuiltinHead(parseRef("""Map.prototype.set"""), List(Param("key", Normal), Param("value", Normal)))
  val ids = List(
    "sec-map.prototype.set",
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
  |      5:p.Value = value
  |      6:return M
  |    } else 1:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  7:if (= key -0.0) key = 0i else 1:{}
  |  8:let p = (new Record("Key" -> key, "Value" -> value))
  |  9:append p -> entries
  |  10:return M
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[MapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[MapData]].""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~ and SameValueZero(_p_.[[Key]], _key_) is *true*, then""",
    """              1. Set _p_.[[Value]] to _value_.""",
    """              1. Return _M_.""",
    """          1. If _key_ is *-0*<sub>𝔽</sub>, set _key_ to *+0*<sub>𝔽</sub>.""",
    """          1. Let _p_ be the Record { [[Key]]: _key_, [[Value]]: _value_ }.""",
    """          1. Append _p_ as the last element of _entries_.""",
    """          1. Return _M_.""",
  )
}
