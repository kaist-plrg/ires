package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.WeakMap.prototype.delete` extends Algo {
  val head = BuiltinHead(parseRef("""WeakMap.prototype.delete"""), List(Param("key", Normal)))
  val ids = List(
    "sec-weakmap.prototype.delete",
    "sec-properties-of-the-weakmap-prototype-object",
    "sec-weakmap-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let M = this
  |  1:app __x0__ = (RequireInternalSlot M "WeakMapData")
  |  1:[? __x0__]
  |  2:let entries = M.WeakMapData
  |  3:if (! (= (typeof key) Object)) return false else 15:{}
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
  |      6:p.Key = CONST_empty
  |      7:p.Value = CONST_empty
  |      8:return true
  |    } else 15:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  9:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _M_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_M_, [[WeakMapData]]).""",
    """          1. Let _entries_ be the List that is _M_.[[WeakMapData]].""",
    """          1. If Type(_key_) is not Object, return *false*.""",
    """          1. For each Record { [[Key]], [[Value]] } _p_ of _entries_, do""",
    """            1. If _p_.[[Key]] is not ~empty~ and SameValue(_p_.[[Key]], _key_) is *true*, then""",
    """              1. Set _p_.[[Key]] to ~empty~.""",
    """              1. Set _p_.[[Value]] to ~empty~.""",
    """              1. Return *true*.""",
    """          1. Return *false*.""",
  )
}
