package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ComposeWriteEventBytes` extends Algo {
  val head = NormalHead("ComposeWriteEventBytes", List(Param("execution", Normal), Param("byteIndex", Normal), Param("Ws", Normal)))
  val ids = List(
    "sec-composewriteeventbytes",
    "sec-abstract-operations-for-the-memory-model",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:let byteLocation = byteIndex
  |  1:let bytesRead = (new [])
  |  2:let __x0__ = Ws
  |  2:let __x1__ = 0i
  |  2:while (< __x1__ __x0__.length) {
  |    let W = __x0__[__x1__]
  |    4:let payloadIndex = (- byteLocation W.ByteIndex)
  |    7:if (is-instance-of W WriteSharedMemory) let byte = W.Payload[payloadIndex] else {
  |      8:assert (is-instance-of W ReadModifyWriteSharedMemory)
  |      9:app __x2__ = (ValueOfReadEvent execution W)
  |      9:let bytes = __x2__
  |      10:app __x3__ = (W.ModifyOp W bytes W.Payload)
  |      10:let bytesModified = __x3__
  |      11:let byte = bytesModified[payloadIndex]
  |    }
  |    12:append byte -> bytesRead
  |    13:byteLocation = (+ byteLocation 1i)
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  14:return bytesRead
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _byteLocation_ be _byteIndex_.""",
    """        1. Let _bytesRead_ be a new empty List.""",
    """        1. For each element _W_ of _Ws_, do""",
    """          1. Assert: _W_ has _byteLocation_ in its range.""",
    """          1. Let _payloadIndex_ be _byteLocation_ - _W_.[[ByteIndex]].""",
    """          1. If _W_ is a WriteSharedMemory event, then""",
    """            1. Let _byte_ be _W_.[[Payload]][_payloadIndex_].""",
    """          1. Else,""",
    """            1. Assert: _W_ is a ReadModifyWriteSharedMemory event.""",
    """            1. Let _bytes_ be ValueOfReadEvent(_execution_, _W_).""",
    """            1. Let _bytesModified_ be _W_.[[ModifyOp]](_bytes_, _W_.[[Payload]]).""",
    """            1. Let _byte_ be _bytesModified_[_payloadIndex_].""",
    """          1. Append _byte_ to _bytesRead_.""",
    """          1. Set _byteLocation_ to _byteLocation_ + 1.""",
    """        1. Return _bytesRead_.""",
  )
}
