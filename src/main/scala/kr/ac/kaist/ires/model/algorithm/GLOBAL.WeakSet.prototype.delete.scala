package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.WeakSet.prototype.delete` extends Algo {
  val head = BuiltinHead(parseRef("""WeakSet.prototype.delete"""), List(Param("value", Normal)))
  val ids = List(
    "sec-weakset.prototype.delete",
    "sec-properties-of-the-weakset-prototype-object",
    "sec-weakset-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (RequireInternalSlot S "WeakSetData")
  |  1:[? __x0__]
  |  2:if (! (= (typeof value) Object)) return false else 15:{}
  |  3:let entries = S.WeakSetData
  |  4:let __x1__ = entries
  |  4:let __x2__ = 0i
  |  4:while (< __x2__ __x1__.length) {
  |    let e = __x1__[__x2__]
  |    5:let __x3__ = true
  |    5:__x3__ = (! (= e CONST_empty))
  |    5:if __x3__ {
  |      app __x4__ = (SameValue e value)
  |      __x3__ = (= __x4__ true)
  |    } else 15:{}
  |    5:if __x3__ {
  |      6:??? "Replace the element of id:{entries} whose value is id:{e} with an element whose value is const:{empty} ."
  |      7:return true
  |    } else 15:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  8:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_S_, [[WeakSetData]]).""",
    """          1. If Type(_value_) is not Object, return *false*.""",
    """          1. Let _entries_ be the List that is _S_.[[WeakSetData]].""",
    """          1. For each element _e_ of _entries_, do""",
    """            1. If _e_ is not ~empty~ and SameValue(_e_, _value_) is *true*, then""",
    """              1. Replace the element of _entries_ whose value is _e_ with an element whose value is ~empty~.""",
    """              1. Return *true*.""",
    """          1. Return *false*.""",
  )
}
