package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.FinalizationRegistry.prototype.unregister` extends Algo {
  val head = BuiltinHead(parseRef("""FinalizationRegistry.prototype.unregister"""), List(Param("unregisterToken", Normal)))
  val ids = List(
    "sec-finalization-registry.prototype.unregister",
    "sec-properties-of-the-finalization-registry-prototype-object",
    "sec-finalization-registry-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:let finalizationRegistry = this
  |  1:app __x0__ = (RequireInternalSlot finalizationRegistry "Cells")
  |  1:[? __x0__]
  |  2:if (! (= (typeof unregisterToken) Object)) throw TypeError else 7:{}
  |  3:let removed = false
  |  4:let __x1__ = finalizationRegistry.Cells
  |  4:let __x2__ = 0i
  |  4:while (< __x2__ __x1__.length) {
  |    let cell = __x1__[__x2__]
  |    5:let __x3__ = true
  |    5:__x3__ = (! (= cell.UnregisterToken CONST_empty))
  |    5:if __x3__ {
  |      app __x4__ = (SameValue cell.UnregisterToken unregisterToken)
  |      __x3__ = (= __x4__ true)
  |    } else 7:{}
  |    5:if __x3__ {
  |      6:??? "Remove id:{cell} from id:{finalizationRegistry} . [ [ Cells ] ] ."
  |      7:removed = true
  |    } else 7:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  8:return removed
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _finalizationRegistry_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_finalizationRegistry_, [[Cells]]).""",
    """          1. If Type(_unregisterToken_) is not Object, throw a *TypeError* exception.""",
    """          1. Let _removed_ be *false*.""",
    """          1. For each Record { [[WeakRefTarget]], [[HeldValue]], [[UnregisterToken]] } _cell_ of _finalizationRegistry_.[[Cells]], do""",
    """            1. If _cell_.[[UnregisterToken]] is not ~empty~ and SameValue(_cell_.[[UnregisterToken]], _unregisterToken_) is *true*, then""",
    """              1. Remove _cell_ from _finalizationRegistry_.[[Cells]].""",
    """              1. Set _removed_ to *true*.""",
    """          1. Return _removed_.""",
  )
}
