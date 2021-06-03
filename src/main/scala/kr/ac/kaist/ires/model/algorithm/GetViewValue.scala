package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetViewValue` extends Algo {
  val head = NormalHead("GetViewValue", List(Param("view", Normal), Param("requestIndex", Normal), Param("isLittleEndian", Normal), Param("type", Normal)))
  val ids = List(
    "sec-getviewvalue",
    "sec-abstract-operations-for-dataview-objects",
    "sec-dataview-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireInternalSlot view "DataView")
  |  0:[? __x0__]
  |  1:assert (! (= view.ViewedArrayBuffer absent))
  |  2:app __x1__ = (ToIndex requestIndex)
  |  2:let getIndex = [? __x1__]
  |  3:app __x2__ = (ToBoolean isLittleEndian)
  |  3:isLittleEndian = [! __x2__]
  |  4:let buffer = view.ViewedArrayBuffer
  |  5:app __x3__ = (IsDetachedBuffer buffer)
  |  5:if (= __x3__ true) throw TypeError else 0:{}
  |  6:let viewOffset = view.ByteOffset
  |  7:let viewSize = view.ByteLength
  |  8:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{type} ."
  |  9:if (< viewSize (+ getIndex elementSize)) throw RangeError else 0:{}
  |  10:let bufferIndex = (+ getIndex viewOffset)
  |  11:app __x4__ = (GetValueFromBuffer buffer bufferIndex type false CONST_Unordered isLittleEndian)
  |  11:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireInternalSlot(_view_, [[DataView]]).""",
    """          1. Assert: _view_ has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _getIndex_ be ? ToIndex(_requestIndex_).""",
    """          1. Set _isLittleEndian_ to ! ToBoolean(_isLittleEndian_).""",
    """          1. Let _buffer_ be _view_.[[ViewedArrayBuffer]].""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _viewOffset_ be _view_.[[ByteOffset]].""",
    """          1. Let _viewSize_ be _view_.[[ByteLength]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _type_.""",
    """          1. If _getIndex_ + _elementSize_ > _viewSize_, throw a *RangeError* exception.""",
    """          1. Let _bufferIndex_ be _getIndex_ + _viewOffset_.""",
    """          1. Return GetValueFromBuffer(_buffer_, _bufferIndex_, _type_, *false*, ~Unordered~, _isLittleEndian_).""",
  )
}
