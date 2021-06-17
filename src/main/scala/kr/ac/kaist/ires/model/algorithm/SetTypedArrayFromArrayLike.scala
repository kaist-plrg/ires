package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetTypedArrayFromArrayLike` extends Algo {
  val head = NormalHead("SetTypedArrayFromArrayLike", List(Param("target", Normal), Param("targetOffset", Normal), Param("source", Normal)))
  val ids = List(
    "sec-settypedarrayfromarraylike",
    "sec-%typedarray%.prototype.set",
    "sec-properties-of-the-%typedarrayprototype%-object",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  1:let targetBuffer = target.ViewedArrayBuffer
  |  2:app __x0__ = (IsDetachedBuffer targetBuffer)
  |  2:if (= __x0__ true) throw TypeError else 0:{}
  |  3:let targetLength = target.ArrayLength
  |  4:let targetName = target.TypedArrayName
  |  5:??? "Let id:{targetElementSize} be the Element Size value specified in link:{table-the-typedarray-constructors} for id:{targetName} ."
  |  6:??? "Let id:{targetType} be the Element Type value in link:{table-the-typedarray-constructors} for id:{targetName} ."
  |  7:let targetByteOffset = target.ByteOffset
  |  8:app __x1__ = (ToObject source)
  |  8:let src = [? __x1__]
  |  9:app __x2__ = (LengthOfArrayLike src)
  |  9:let srcLength = [? __x2__]
  |  10:if (= targetOffset Infinity) throw RangeError else 0:{}
  |  11:if (< targetLength (+ srcLength targetOffset)) throw RangeError else 0:{}
  |  12:let targetByteIndex = (+ (* targetOffset targetElementSize) targetByteOffset)
  |  13:let k = 0i
  |  14:let limit = (* (+ targetByteIndex targetElementSize) srcLength)
  |  15:while (< targetByteIndex limit) {
  |    16:app __x3__ = (ToString k)
  |    16:let Pk = [! __x3__]
  |    17:app __x4__ = (Get src Pk)
  |    17:let value = [? __x4__]
  |    19:if (= target.ContentType CONST_BigInt) {
  |      app __x5__ = (ToBigInt value)
  |      value = [? __x5__]
  |    } else {
  |      app __x6__ = (ToNumber value)
  |      value = [? __x6__]
  |    }
  |    20:app __x7__ = (IsDetachedBuffer targetBuffer)
  |    20:if (= __x7__ true) throw TypeError else 0:{}
  |    21:app __x8__ = (SetValueInBuffer targetBuffer targetByteIndex targetType value true CONST_Unordered)
  |    21:__x8__
  |    22:k = (+ k 1i)
  |    23:targetByteIndex = (+ targetByteIndex targetElementSize)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _source_ is any ECMAScript language value other than an Object with a [[TypedArrayName]] internal slot.""",
    """            1. Let _targetBuffer_ be _target_.[[ViewedArrayBuffer]].""",
    """            1. If IsDetachedBuffer(_targetBuffer_) is *true*, throw a *TypeError* exception.""",
    """            1. Let _targetLength_ be _target_.[[ArrayLength]].""",
    """            1. Let _targetName_ be the String value of _target_.[[TypedArrayName]].""",
    """            1. Let _targetElementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _targetName_.""",
    """            1. Let _targetType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _targetName_.""",
    """            1. Let _targetByteOffset_ be _target_.[[ByteOffset]].""",
    """            1. Let _src_ be ? ToObject(_source_).""",
    """            1. Let _srcLength_ be ? LengthOfArrayLike(_src_).""",
    """            1. If _targetOffset_ is +∞, throw a *RangeError* exception.""",
    """            1. If _srcLength_ + _targetOffset_ > _targetLength_, throw a *RangeError* exception.""",
    """            1. Let _targetByteIndex_ be _targetOffset_ × _targetElementSize_ + _targetByteOffset_.""",
    """            1. Let _k_ be 0.""",
    """            1. Let _limit_ be _targetByteIndex_ + _targetElementSize_ × _srcLength_.""",
    """            1. Repeat, while _targetByteIndex_ < _limit_,""",
    """              1. Let _Pk_ be ! ToString(𝔽(_k_)).""",
    """              1. Let _value_ be ? Get(_src_, _Pk_).""",
    """              1. If _target_.[[ContentType]] is ~BigInt~, set _value_ to ? ToBigInt(_value_).""",
    """              1. Otherwise, set _value_ to ? ToNumber(_value_).""",
    """              1. If IsDetachedBuffer(_targetBuffer_) is *true*, throw a *TypeError* exception.""",
    """              1. Perform SetValueInBuffer(_targetBuffer_, _targetByteIndex_, _targetType_, _value_, *true*, ~Unordered~).""",
    """              1. Set _k_ to _k_ + 1.""",
    """              1. Set _targetByteIndex_ to _targetByteIndex_ + _targetElementSize_.""",
  )
}
