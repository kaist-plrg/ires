package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetViewValue` extends Algo {
  val head = NormalHead("SetViewValue", List(Param("view", Normal), Param("requestIndex", Normal), Param("isLittleEndian", Normal), Param("type", Normal), Param("value", Normal)))
  val ids = List(
    "sec-setviewvalue",
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
  |  4:app __x2__ = (IsBigIntElementType type)
  |  4:if (= [! __x2__] true) {
  |    app __x3__ = (ToBigInt value)
  |    let numberValue = [? __x3__]
  |  } else {
  |    app __x4__ = (ToNumber value)
  |    let numberValue = [? __x4__]
  |  }
  |  5:app __x5__ = (ToBoolean isLittleEndian)
  |  5:isLittleEndian = [! __x5__]
  |  6:let buffer = view.ViewedArrayBuffer
  |  7:app __x6__ = (IsDetachedBuffer buffer)
  |  7:if (= __x6__ true) throw TypeError else 0:{}
  |  8:let viewOffset = view.ByteOffset
  |  9:let viewSize = view.ByteLength
  |  10:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{type} ."
  |  11:if (< viewSize (+ getIndex elementSize)) throw RangeError else 0:{}
  |  12:let bufferIndex = (+ getIndex viewOffset)
  |  13:app __x7__ = (SetValueInBuffer buffer bufferIndex type numberValue false CONST_Unordered isLittleEndian)
  |  13:return __x7__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireInternalSlot(_view_, [[DataView]]).""",
    """          1. Assert: _view_ has a [[ViewedArrayBuffer]] internal slot.""",
    """          1. Let _getIndex_ be ? ToIndex(_requestIndex_).""",
    """          1. If ! IsBigIntElementType(_type_) is *true*, let _numberValue_ be ? ToBigInt(_value_).""",
    """          1. Otherwise, let _numberValue_ be ? ToNumber(_value_).""",
    """          1. Set _isLittleEndian_ to ! ToBoolean(_isLittleEndian_).""",
    """          1. Let _buffer_ be _view_.[[ViewedArrayBuffer]].""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _viewOffset_ be _view_.[[ByteOffset]].""",
    """          1. Let _viewSize_ be _view_.[[ByteLength]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _type_.""",
    """          1. If _getIndex_ + _elementSize_ > _viewSize_, throw a *RangeError* exception.""",
    """          1. Let _bufferIndex_ be _getIndex_ + _viewOffset_.""",
    """          1. Return SetValueInBuffer(_buffer_, _bufferIndex_, _type_, _numberValue_, *false*, ~Unordered~, _isLittleEndian_).""",
  )
}
