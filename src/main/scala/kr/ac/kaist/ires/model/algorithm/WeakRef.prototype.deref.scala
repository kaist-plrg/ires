package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::WeakRef.prototype.deref` extends Algo {
  val head = BuiltinHead(parseRef("""WeakRef.prototype.deref"""), List())
  val ids = List(
    "sec-weak-ref.prototype.deref",
    "sec-properties-of-the-weak-ref-prototype-object",
    "sec-weak-ref-objects",
    "sec-managing-memory",
  )
  val rawBody = parseInst("""{
  |  0:let weakRef = this
  |  1:app __x0__ = (RequireInternalSlot weakRef "WeakRefTarget")
  |  1:[? __x0__]
  |  2:app __x1__ = (WeakRefDeref weakRef)
  |  2:return [! __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _weakRef_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_weakRef_, [[WeakRefTarget]]).""",
    """          1. Return ! WeakRefDeref(_weakRef_).""",
  )
}
