package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.getDataView.prototype.byteOffset` extends Algo {
  val head = BuiltinHead(parseRef("""getDataView.prototype.byteOffset"""), List())
  val ids = List(
    "sec-get-dataview.prototype.byteoffset",
    "sec-properties-of-the-dataview-prototype-object",
    "sec-dataview-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let O = this
  |  1:app __x0__ = (RequireInternalSlot O "DataView")
  |  1:[? __x0__]
  |  2:assert (! (= O.ViewedArrayBuffer absent))
  |  3:let buffer = O.ViewedArrayBuffer
  |  4:app __x1__ = (IsDetachedBuffer buffer)
  |  4:if (= __x1__ true) throw TypeError else 0:{}
  |  5:let offset = O.ByteOffset
  |  6:return offset
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[DataView]]).""",
    """          1. Assert: _O_ has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _buffer_ be _O_.[[ViewedArrayBuffer]].""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _offset_ be _O_.[[ByteOffset]].""",
    """          1. Return 𝔽(_offset_).""",
  )
}
