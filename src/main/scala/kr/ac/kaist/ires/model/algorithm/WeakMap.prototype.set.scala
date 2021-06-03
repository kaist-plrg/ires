package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WeakMap.prototype.set` extends Algo {
  val head = BuiltinHead(parseRef("""WeakMap.prototype.set"""), List(Param("key", Normal), Param("value", Normal)))
  val ids = List(
    "sec-weakmap.prototype.set",
    "sec-properties-of-the-weakmap-prototype-object",
    "sec-weakmap-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (RequireInternalSlot M "WeakMapData")
  |  1:[? __x0__]
  |  2:let entries = M.WeakMapData
  |  3:if (! (= (typeof key) Object)) throw TypeError else 15:{}
  |  4:let __x1__ = entries
  |  4:let __x2__ = 0i
  |  4:while (< __x2__ __x1__.length) {
  |    let p = __x1__[__x2__]
  |    5:let __x3__ = true
  |    5:__x3__ = (! (= p.Key CONST_empty))
  |    5:if __x3__ {
  |      app __x4__ = (SameValue p.Key key)
  |      __x3__ = (= __x4__ true)
  |    } else 15:{}
  |    5:if __x3__ {
  |      6:p.Value = value
  |      7:return M
  |    } else 15:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  8:let p = (new Record("Key" -> key, "Value" -> value))
  |  9:append p -> entries
  |  10:return M
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[WeakMapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[WeakMapData]].""",
    """          1. If Type(_key_) is not Object, throw a *TypeError* exception.""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~ and SameValue(_p_.[[Key]], _key_) is *true*, then""",
    """              1. Set _p_.[[Value]] to _value_.""",
    """              1. Return _M_.""",
    """          1. Let _p_ be the Record { [[Key]]: _key_, [[Value]]: _value_ }.""",
    """          1. Append _p_ as the last element of _entries_.""",
    """          1. Return _M_.""",
  )
}
