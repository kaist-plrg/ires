package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeTypedArrayFromTypedArray` extends Algo {
  val head = NormalHead("InitializeTypedArrayFromTypedArray", List(Param("O", Normal), Param("srcArray", Normal)))
  val ids = List(
    "sec-initializetypedarrayfromtypedarray",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  2:let srcData = srcArray.ViewedArrayBuffer
  |  3:app __x0__ = (IsDetachedBuffer srcData)
  |  3:if (= __x0__ true) throw TypeError else 1:{}
  |  4:let constructorName = O.TypedArrayName
  |  5:??? "Let id:{elementType} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{constructorName} ."
  |  6:let elementLength = srcArray.ArrayLength
  |  7:let srcName = srcArray.TypedArrayName
  |  8:??? "Let id:{srcType} be the Element Type value in link:{unhandled: table-the-typedarray-constructors} for id:{srcName} ."
  |  9:??? "Let id:{srcElementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{srcName} ."
  |  10:let srcByteOffset = srcArray.ByteOffset
  |  11:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{constructorName} ."
  |  12:let byteLength = (* elementSize elementLength)
  |  15:app __x1__ = (IsSharedArrayBuffer srcData)
  |  15:if (= __x1__ false) {
  |    14:app __x2__ = (SpeciesConstructor srcData INTRINSIC_ArrayBuffer)
  |    14:let bufferConstructor = [? __x2__]
  |  } else let bufferConstructor = INTRINSIC_ArrayBuffer
  |  19:if (= elementType srcType) {
  |    18:app __x3__ = (CloneArrayBuffer srcData srcByteOffset byteLength bufferConstructor)
  |    18:let data = [? __x3__]
  |  } else {
  |    20:app __x4__ = (AllocateArrayBuffer bufferConstructor byteLength)
  |    20:let data = [? __x4__]
  |    21:app __x5__ = (IsDetachedBuffer srcData)
  |    21:if (= __x5__ true) throw TypeError else 1:{}
  |    22:if (! (== srcArray.ContentType O.ContentType)) throw TypeError else 1:{}
  |    23:let srcByteIndex = srcByteOffset
  |    24:let targetByteIndex = 0i
  |    25:let count = elementLength
  |    26:while (< 0i count) {
  |      27:app __x6__ = (GetValueFromBuffer srcData srcByteIndex srcType true CONST_Unordered)
  |      27:let value = __x6__
  |      28:app __x7__ = (SetValueInBuffer data targetByteIndex elementType value true CONST_Unordered)
  |      28:__x7__
  |      29:srcByteIndex = (+ srcByteIndex srcElementSize)
  |      30:targetByteIndex = (+ targetByteIndex elementSize)
  |      31:count = (- count 1i)
  |    }
  |  }
  |  32:O.ViewedArrayBuffer = data
  |  33:O.ByteLength = byteLength
  |  34:O.ByteOffset = 0i
  |  35:O.ArrayLength = elementLength
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _O_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Assert: _srcArray_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Let _srcData_ be _srcArray_.[[ViewedArrayBuffer]].""",
    """            1. If IsDetachedBuffer(_srcData_) is *true*, throw a *TypeError* exception.""",
    """            1. Let _constructorName_ be the String value of _O_.[[TypedArrayName]].""",
    """            1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _constructorName_.""",
    """            1. Let _elementLength_ be _srcArray_.[[ArrayLength]].""",
    """            1. Let _srcName_ be the String value of _srcArray_.[[TypedArrayName]].""",
    """            1. Let _srcType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _srcName_.""",
    """            1. Let _srcElementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _srcName_.""",
    """            1. Let _srcByteOffset_ be _srcArray_.[[ByteOffset]].""",
    """            1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _constructorName_.""",
    """            1. Let _byteLength_ be _elementSize_ × _elementLength_.""",
    """            1. If IsSharedArrayBuffer(_srcData_) is *false*, then""",
    """              1. Let _bufferConstructor_ be ? SpeciesConstructor(_srcData_, %ArrayBuffer%).""",
    """            1. Else,""",
    """              1. Let _bufferConstructor_ be %ArrayBuffer%.""",
    """            1. If _elementType_ is the same as _srcType_, then""",
    """              1. Let _data_ be ? CloneArrayBuffer(_srcData_, _srcByteOffset_, _byteLength_, _bufferConstructor_).""",
    """            1. Else,""",
    """              1. Let _data_ be ? AllocateArrayBuffer(_bufferConstructor_, _byteLength_).""",
    """              1. If IsDetachedBuffer(_srcData_) is *true*, throw a *TypeError* exception.""",
    """              1. If _srcArray_.[[ContentType]] ≠ _O_.[[ContentType]], throw a *TypeError* exception.""",
    """              1. Let _srcByteIndex_ be _srcByteOffset_.""",
    """              1. Let _targetByteIndex_ be 0.""",
    """              1. Let _count_ be _elementLength_.""",
    """              1. Repeat, while _count_ > 0,""",
    """                1. Let _value_ be GetValueFromBuffer(_srcData_, _srcByteIndex_, _srcType_, *true*, ~Unordered~).""",
    """                1. Perform SetValueInBuffer(_data_, _targetByteIndex_, _elementType_, _value_, *true*, ~Unordered~).""",
    """                1. Set _srcByteIndex_ to _srcByteIndex_ + _srcElementSize_.""",
    """                1. Set _targetByteIndex_ to _targetByteIndex_ + _elementSize_.""",
    """                1. Set _count_ to _count_ - 1.""",
    """            1. Set _O_.[[ViewedArrayBuffer]] to _data_.""",
    """            1. Set _O_.[[ByteLength]] to _byteLength_.""",
    """            1. Set _O_.[[ByteOffset]] to 0.""",
    """            1. Set _O_.[[ArrayLength]] to _elementLength_.""",
  )
}
