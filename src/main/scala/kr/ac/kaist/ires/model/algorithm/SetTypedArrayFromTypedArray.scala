package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetTypedArrayFromTypedArray` extends Algo {
  val head = NormalHead("SetTypedArrayFromTypedArray", List(Param("target", Normal), Param("targetOffset", Normal), Param("source", Normal)))
  val ids = List(
    "sec-settypedarrayfromtypedarray",
    "sec-%typedarray%.prototype.set",
    "sec-properties-of-the-%typedarrayprototype%-object",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:let targetBuffer = target.ViewedArrayBuffer
  |  2:app __x0__ = (IsDetachedBuffer targetBuffer)
  |  2:if (= __x0__ true) throw TypeError else 30:{}
  |  3:let targetLength = target.ArrayLength
  |  4:let srcBuffer = source.ViewedArrayBuffer
  |  5:app __x1__ = (IsDetachedBuffer srcBuffer)
  |  5:if (= __x1__ true) throw TypeError else 30:{}
  |  6:let targetName = target.TypedArrayName
  |  7:??? "Let id:{targetType} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{targetName} ."
  |  8:??? "Let id:{targetElementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{targetName} ."
  |  9:let targetByteOffset = target.ByteOffset
  |  10:let srcName = source.TypedArrayName
  |  11:??? "Let id:{srcType} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{srcName} ."
  |  12:??? "Let id:{srcElementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{srcName} ."
  |  13:let srcLength = source.ArrayLength
  |  14:let srcByteOffset = source.ByteOffset
  |  15:if (= targetOffset Infinity) throw RangeError else 30:{}
  |  16:if (< targetLength (+ srcLength targetOffset)) throw RangeError else 30:{}
  |  17:if (! (== target.ContentType source.ContentType)) throw TypeError else 30:{}
  |  20:app __x2__ = (IsSharedArrayBuffer srcBuffer)
  |  20:app __x3__ = (IsSharedArrayBuffer targetBuffer)
  |  20:if (&& (= __x2__ true) (= __x3__ true)) if (= srcBuffer.ArrayBufferData targetBuffer.ArrayBufferData) let same = true else let same = false else {
  |    app __x4__ = (SameValue srcBuffer targetBuffer)
  |    let same = __x4__
  |  }
  |  26:if (= same true) {
  |    22:let srcByteLength = source.ByteLength
  |    23:app __x5__ = (CloneArrayBuffer srcBuffer srcByteOffset srcByteLength INTRINSIC_ArrayBuffer)
  |    23:srcBuffer = [? __x5__]
  |    25:let srcByteIndex = 0i
  |  } else let srcByteIndex = srcByteOffset
  |  27:let targetByteIndex = (+ (* targetOffset targetElementSize) targetByteOffset)
  |  28:let limit = (* (+ targetByteIndex targetElementSize) srcLength)
  |  36:if (= srcType targetType) while (< targetByteIndex limit) {
  |    32:app __x6__ = (GetValueFromBuffer srcBuffer srcByteIndex CONST_Uint8 true CONST_Unordered)
  |    32:let value = __x6__
  |    33:app __x7__ = (SetValueInBuffer targetBuffer targetByteIndex CONST_Uint8 value true CONST_Unordered)
  |    33:__x7__
  |    34:srcByteIndex = (+ srcByteIndex 1i)
  |    35:targetByteIndex = (+ targetByteIndex 1i)
  |  } else while (< targetByteIndex limit) {
  |    38:app __x8__ = (GetValueFromBuffer srcBuffer srcByteIndex srcType true CONST_Unordered)
  |    38:let value = __x8__
  |    39:app __x9__ = (SetValueInBuffer targetBuffer targetByteIndex targetType value true CONST_Unordered)
  |    39:__x9__
  |    40:srcByteIndex = (+ srcByteIndex srcElementSize)
  |    41:targetByteIndex = (+ targetByteIndex targetElementSize)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _source_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Let _targetBuffer_ be _target_.[[ViewedArrayBuffer]].""",
    """            1. If IsDetachedBuffer(_targetBuffer_) is *true*, throw a *TypeError* exception.""",
    """            1. Let _targetLength_ be _target_.[[ArrayLength]].""",
    """            1. Let _srcBuffer_ be _source_.[[ViewedArrayBuffer]].""",
    """            1. If IsDetachedBuffer(_srcBuffer_) is *true*, throw a *TypeError* exception.""",
    """            1. Let _targetName_ be the String value of _target_.[[TypedArrayName]].""",
    """            1. Let _targetType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _targetName_.""",
    """            1. Let _targetElementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _targetName_.""",
    """            1. Let _targetByteOffset_ be _target_.[[ByteOffset]].""",
    """            1. Let _srcName_ be the String value of _source_.[[TypedArrayName]].""",
    """            1. Let _srcType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _srcName_.""",
    """            1. Let _srcElementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _srcName_.""",
    """            1. Let _srcLength_ be _source_.[[ArrayLength]].""",
    """            1. Let _srcByteOffset_ be _source_.[[ByteOffset]].""",
    """            1. If _targetOffset_ is +∞, throw a *RangeError* exception.""",
    """            1. If _srcLength_ + _targetOffset_ > _targetLength_, throw a *RangeError* exception.""",
    """            1. If _target_.[[ContentType]] ≠ _source_.[[ContentType]], throw a *TypeError* exception.""",
    """            1. If both IsSharedArrayBuffer(_srcBuffer_) and IsSharedArrayBuffer(_targetBuffer_) are *true*, then""",
    """              1. If _srcBuffer_.[[ArrayBufferData]] and _targetBuffer_.[[ArrayBufferData]] are the same Shared Data Block values, let _same_ be *true*; else let _same_ be *false*.""",
    """            1. Else, let _same_ be SameValue(_srcBuffer_, _targetBuffer_).""",
    """            1. If _same_ is *true*, then""",
    """              1. Let _srcByteLength_ be _source_.[[ByteLength]].""",
    """              1. Set _srcBuffer_ to ? CloneArrayBuffer(_srcBuffer_, _srcByteOffset_, _srcByteLength_, %ArrayBuffer%).""",
    """              1. NOTE: %ArrayBuffer% is used to clone _srcBuffer_ because is it known to not have any observable side-effects.""",
    """              1. Let _srcByteIndex_ be 0.""",
    """            1. Else, let _srcByteIndex_ be _srcByteOffset_.""",
    """            1. Let _targetByteIndex_ be _targetOffset_ × _targetElementSize_ + _targetByteOffset_.""",
    """            1. Let _limit_ be _targetByteIndex_ + _targetElementSize_ × _srcLength_.""",
    """            1. If _srcType_ is the same as _targetType_, then""",
    """              1. NOTE: If _srcType_ and _targetType_ are the same, the transfer must be performed in a manner that preserves the bit-level encoding of the source data.""",
    """              1. Repeat, while _targetByteIndex_ < _limit_,""",
    """                1. Let _value_ be GetValueFromBuffer(_srcBuffer_, _srcByteIndex_, ~Uint8~, *true*, ~Unordered~).""",
    """                1. Perform SetValueInBuffer(_targetBuffer_, _targetByteIndex_, ~Uint8~, _value_, *true*, ~Unordered~).""",
    """                1. Set _srcByteIndex_ to _srcByteIndex_ + 1.""",
    """                1. Set _targetByteIndex_ to _targetByteIndex_ + 1.""",
    """            1. Else,""",
    """              1. Repeat, while _targetByteIndex_ < _limit_,""",
    """                1. Let _value_ be GetValueFromBuffer(_srcBuffer_, _srcByteIndex_, _srcType_, *true*, ~Unordered~).""",
    """                1. Perform SetValueInBuffer(_targetBuffer_, _targetByteIndex_, _targetType_, _value_, *true*, ~Unordered~).""",
    """                1. Set _srcByteIndex_ to _srcByteIndex_ + _srcElementSize_.""",
    """                1. Set _targetByteIndex_ to _targetByteIndex_ + _targetElementSize_.""",
  )
}
