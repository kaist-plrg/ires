package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DataView` extends Algo {
  val head = BuiltinHead(parseRef("""DataView"""), List(Param("buffer", Normal), Param("byteOffset", Optional), Param("byteLength", Optional)))
  val ids = List(
    "sec-dataview-buffer-byteoffset-bytelength",
    "sec-dataview-constructor",
    "sec-dataview-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 0:{}
  |  1:app __x0__ = (RequireInternalSlot buffer "ArrayBufferData")
  |  1:[? __x0__]
  |  2:app __x1__ = (ToIndex byteOffset)
  |  2:let offset = [? __x1__]
  |  3:app __x2__ = (IsDetachedBuffer buffer)
  |  3:if (= __x2__ true) throw TypeError else 0:{}
  |  4:let bufferByteLength = buffer.ArrayBufferByteLength
  |  5:if (< bufferByteLength offset) throw RangeError else 0:{}
  |  8:if (= byteLength undefined) let viewByteLength = (- bufferByteLength offset) else {
  |    9:app __x3__ = (ToIndex byteLength)
  |    9:let viewByteLength = [? __x3__]
  |    10:if (< bufferByteLength (+ offset viewByteLength)) throw RangeError else 0:{}
  |  }
  |  11:app __x4__ = (OrdinaryCreateFromConstructor NewTarget "%DataView.prototype%" (new ["DataView", "ViewedArrayBuffer", "ByteLength", "ByteOffset"]))
  |  11:let O = [? __x4__]
  |  12:app __x5__ = (IsDetachedBuffer buffer)
  |  12:if (= __x5__ true) throw TypeError else 0:{}
  |  13:O.ViewedArrayBuffer = buffer
  |  14:O.ByteLength = viewByteLength
  |  15:O.ByteOffset = offset
  |  16:return O
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Perform ? RequireInternalSlot(_buffer_, [[ArrayBufferData]]).""",
    """          1. Let _offset_ be ? ToIndex(_byteOffset_).""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Let _bufferByteLength_ be _buffer_.[[ArrayBufferByteLength]].""",
    """          1. If _offset_ > _bufferByteLength_, throw a *RangeError* exception.""",
    """          1. If _byteLength_ is *undefined*, then""",
    """            1. Let _viewByteLength_ be _bufferByteLength_ - _offset_.""",
    """          1. Else,""",
    """            1. Let _viewByteLength_ be ? ToIndex(_byteLength_).""",
    """            1. If _offset_ + _viewByteLength_ > _bufferByteLength_, throw a *RangeError* exception.""",
    """          1. Let _O_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%DataView.prototype%"*, « [[DataView]], [[ViewedArrayBuffer]], [[ByteLength]], [[ByteOffset]] »).""",
    """          1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """          1. Set _O_.[[ViewedArrayBuffer]] to _buffer_.""",
    """          1. Set _O_.[[ByteLength]] to _viewByteLength_.""",
    """          1. Set _O_.[[ByteOffset]] to _offset_.""",
    """          1. Return _O_.""",
  )
}
