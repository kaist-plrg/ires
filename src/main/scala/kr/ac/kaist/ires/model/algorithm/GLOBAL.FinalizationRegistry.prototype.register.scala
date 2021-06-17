package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.FinalizationRegistry.prototype.register` extends Algo {
  val head = BuiltinHead(parseRef("""FinalizationRegistry.prototype.register"""), List(Param("target", Normal), Param("heldValue", Normal), Param("unregisterToken", Optional)))
  val ids = List(
    "sec-finalization-registry.prototype.register",
    "sec-properties-of-the-finalization-registry-prototype-object",
    "sec-finalization-registry-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:let finalizationRegistry = this
  |  1:app __x0__ = (RequireInternalSlot finalizationRegistry "Cells")
  |  1:[? __x0__]
  |  2:if (! (= (typeof target) Object)) throw TypeError else 7:{}
  |  3:app __x1__ = (SameValue target heldValue)
  |  3:if (= __x1__ true) throw TypeError else 7:{}
  |  4:if (! (= (typeof unregisterToken) Object)) {
  |    5:if (! (= unregisterToken undefined)) throw TypeError else 7:{}
  |    6:unregisterToken = CONST_empty
  |  } else 7:{}
  |  7:let cell = (new Record("WeakRefTarget" -> target, "HeldValue" -> heldValue, "UnregisterToken" -> unregisterToken))
  |  8:append cell -> finalizationRegistry.Cells
  |  9:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _finalizationRegistry_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_finalizationRegistry_, [[Cells]]).""",
    """          1. If Type(_target_) is not Object, throw a *TypeError* exception.""",
    """          1. If SameValue(_target_, _heldValue_) is *true*, throw a *TypeError* exception.""",
    """          1. If Type(_unregisterToken_) is not Object, then""",
    """            1. If _unregisterToken_ is not *undefined*, throw a *TypeError* exception.""",
    """            1. Set _unregisterToken_ to ~empty~.""",
    """          1. Let _cell_ be the Record { [[WeakRefTarget]]: _target_, [[HeldValue]]: _heldValue_, [[UnregisterToken]]: _unregisterToken_ }.""",
    """          1. Append _cell_ to _finalizationRegistry_.[[Cells]].""",
    """          1. Return *undefined*.""",
  )
}
