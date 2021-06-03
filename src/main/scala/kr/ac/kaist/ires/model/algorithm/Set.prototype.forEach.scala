package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Set.prototype.forEach` extends Algo {
  val head = BuiltinHead(parseRef("""Set.prototype.forEach"""), List(Param("callbackfn", Normal), Param("thisArg", Optional)))
  val ids = List(
    "sec-set.prototype.foreach",
    "sec-properties-of-the-set-prototype-object",
    "sec-set-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:let S = this
  |  1:app __x0__ = (RequireInternalSlot S "SetData")
  |  1:[? __x0__]
  |  2:app __x1__ = (IsCallable callbackfn)
  |  2:if (= __x1__ false) throw TypeError else 16:{}
  |  3:let entries = S.SetData
  |  4:let __x2__ = entries
  |  4:let __x3__ = 0i
  |  4:while (< __x3__ __x2__.length) {
  |    let e = __x2__[__x3__]
  |    5:if (! (= e CONST_empty)) {
  |      6:app __x4__ = (Call callbackfn thisArg (new [e, e, S]))
  |      6:[? __x4__]
  |    } else 16:{}
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  7:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _S_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_S_, [[SetData]]).""",
    """          1. If IsCallable(_callbackfn_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _entries_ be the List that is _S_.[[SetData]].""",
    """          1. For each element _e_ of _entries_, do""",
    """            1. If _e_ is not ~empty~, then""",
    """              1. Perform ? Call(_callbackfn_, _thisArg_, « _e_, _e_, _S_ »).""",
    """          1. Return *undefined*.""",
  )
}
