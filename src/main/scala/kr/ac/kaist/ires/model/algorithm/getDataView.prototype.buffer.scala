package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::getDataView.prototype.buffer` extends Algo {
  val head = BuiltinHead(parseRef("""getDataView.prototype.buffer"""), List())
  val ids = List(
    "sec-get-dataview.prototype.buffer",
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
  |  4:return buffer
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _O_ be the *this* value.""",
    """          1. Perform ? RequireInternalSlot(_O_, [[DataView]]).""",
    """          1. Assert: _O_ has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _buffer_ be _O_.[[ViewedArrayBuffer]].""",
    """          1. Return _buffer_.""",
  )
}
