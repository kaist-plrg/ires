package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValidateTypedArray` extends Algo {
  val head = NormalHead("ValidateTypedArray", List(Param("O", Normal)))
  val ids = List(
    "sec-validatetypedarray",
    "sec-abstract-operations-for-typedarray-objects",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireInternalSlot O "TypedArrayName")
  |  0:[? __x0__]
  |  1:assert (! (= O.ViewedArrayBuffer absent))
  |  2:let buffer = O.ViewedArrayBuffer
  |  3:app __x1__ = (IsDetachedBuffer buffer)
  |  3:if (= __x1__ true) throw TypeError else 4:{}
  |  4:return buffer
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireInternalSlot(_O_, [[TypedArrayName]]).""",
    """          1. Assert: _O_ has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _buffer_ be _O_.[[ViewedArrayBuffer]].""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Return _buffer_.""",
  )
}
