package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeTypedArrayFromArrayBuffer` extends Algo {
  val head = NormalHead("InitializeTypedArrayFromArrayBuffer", List(Param("O", Normal), Param("buffer", Normal), Param("byteOffset", Normal), Param("length", Normal)))
  val ids = List(
    "sec-initializetypedarrayfromarraybuffer",
    "sec-typedarray",
    "sec-typedarray-constructors",
    "sec-typedarray-objects",
    "sec-indexed-collections",
  )
  val rawBody = parseInst("""{
  |  2:let constructorName = O.TypedArrayName
  |  3:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for id:{constructorName} ."
  |  4:app __x0__ = (ToIndex byteOffset)
  |  4:let offset = [? __x0__]
  |  5:if (! (== (%% offset elementSize) 0i)) throw RangeError else 1:{}
  |  6:if (! (= length undefined)) {
  |    7:app __x1__ = (ToIndex length)
  |    7:let newLength = [? __x1__]
  |  } else 1:{}
  |  8:app __x2__ = (IsDetachedBuffer buffer)
  |  8:if (= __x2__ true) throw TypeError else 1:{}
  |  9:let bufferByteLength = buffer.ArrayBufferByteLength
  |  14:if (= length undefined) {
  |    11:if (! (== (%% bufferByteLength elementSize) 0i)) throw RangeError else 1:{}
  |    12:let newByteLength = (- bufferByteLength offset)
  |    13:if (< newByteLength 0i) throw RangeError else 1:{}
  |  } else {
  |    15:let newByteLength = (* newLength elementSize)
  |    16:if (< bufferByteLength (+ offset newByteLength)) throw RangeError else 1:{}
  |  }
  |  17:O.ViewedArrayBuffer = buffer
  |  18:O.ByteLength = newByteLength
  |  19:O.ByteOffset = offset
  |  20:O.ArrayLength = (/ newByteLength elementSize)
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: _O_ is an Object that has a [[TypedArrayName]] internal slot.""",
    """            1. Assert: _buffer_ is an Object that has an [[ArrayBufferData]] internal slot.""",
    """            1. Let _constructorName_ be the String value of _O_.[[TypedArrayName]].""",
    """            1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _constructorName_.""",
    """            1. Let _offset_ be ? ToIndex(_byteOffset_).""",
    """            1. If _offset_ modulo _elementSize_ ≠ 0, throw a *RangeError* exception.""",
    """            1. If _length_ is not *undefined*, then""",
    """              1. Let _newLength_ be ? ToIndex(_length_).""",
    """            1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """            1. Let _bufferByteLength_ be _buffer_.[[ArrayBufferByteLength]].""",
    """            1. If _length_ is *undefined*, then""",
    """              1. If _bufferByteLength_ modulo _elementSize_ ≠ 0, throw a *RangeError* exception.""",
    """              1. Let _newByteLength_ be _bufferByteLength_ - _offset_.""",
    """              1. If _newByteLength_ < 0, throw a *RangeError* exception.""",
    """            1. Else,""",
    """              1. Let _newByteLength_ be _newLength_ × _elementSize_.""",
    """              1. If _offset_ + _newByteLength_ > _bufferByteLength_, throw a *RangeError* exception.""",
    """            1. Set _O_.[[ViewedArrayBuffer]] to _buffer_.""",
    """            1. Set _O_.[[ByteLength]] to _newByteLength_.""",
    """            1. Set _O_.[[ByteOffset]] to _offset_.""",
    """            1. Set _O_.[[ArrayLength]] to _newByteLength_ / _elementSize_.""",
  )
}
