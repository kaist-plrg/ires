package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.compareExchange` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.compareExchange"""), List(Param("typedArray", Normal), Param("index", Normal), Param("expectedValue", Normal), Param("replacementValue", Normal)))
  val ids = List(
    "sec-atomics.compareexchange",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ValidateIntegerTypedArray typedArray)
  |  0:let buffer = [? __x0__]
  |  1:let block = buffer.ArrayBufferData
  |  2:app __x1__ = (ValidateAtomicAccess typedArray index)
  |  2:let indexedPosition = [? __x1__]
  |  3:let arrayTypeName = typedArray.TypedArrayName
  |  7:if (= typedArray.ContentType CONST_BigInt) {
  |    5:app __x2__ = (ToBigInt expectedValue)
  |    5:let expected = [? __x2__]
  |    6:app __x3__ = (ToBigInt replacementValue)
  |    6:let replacement = [? __x3__]
  |  } else {
  |    8:app __x4__ = (ToIntegerOrInfinity expectedValue)
  |    8:let expected = [? __x4__]
  |    9:app __x5__ = (ToIntegerOrInfinity replacementValue)
  |    9:let replacement = [? __x5__]
  |  }
  |  10:app __x6__ = (IsDetachedBuffer buffer)
  |  10:if (= __x6__ true) throw TypeError else 22:{}
  |  12:let elementType = CONST_Int8
  |  13:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{elementType} ."
  |  14:let isLittleEndian = AGENT.LittleEndian
  |  15:app __x7__ = (NumericToRawBytes elementType expected isLittleEndian)
  |  15:let expectedBytes = __x7__
  |  16:app __x8__ = (NumericToRawBytes elementType replacement isLittleEndian)
  |  16:let replacementBytes = __x8__
  |  31:app __x9__ = (IsSharedArrayBuffer buffer)
  |  31:if (= __x9__ true) {
  |    18:let execution = AGENT.CandidateExecution
  |    19:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |    20:??? "Let id:{rawBytesRead} be a List of length id:{elementSize} whose elements are nondeterministically chosen byte values ."
  |    27:app __x10__ = (ByteListEqual rawBytesRead expectedBytes)
  |    27:if (= __x10__ true) {
  |      24:??? "Let id:{second} be a new read - modify - write modification function with parameters ( id:{oldBytes} , id:{newBytes} ) that captures nothing and performs the following steps atomically when called : in:{} out:{}"
  |      26:let event = (new ReadModifyWriteSharedMemory("Order" -> CONST_SeqCst, "NoTear" -> true, "Block" -> block, "ByteIndex" -> indexedPosition, "ElementSize" -> elementSize, "Payload" -> replacementBytes, "ModifyOp" -> second))
  |    } else let event = (new ReadSharedMemory("Order" -> CONST_SeqCst, "NoTear" -> true, "Block" -> block, "ByteIndex" -> indexedPosition, "ElementSize" -> elementSize))
  |    29:append event -> eventList
  |    30:append (new ChosenValueRecord("Event" -> event, "ChosenValue" -> rawBytesRead)) -> execution.ChosenValues
  |  } else {
  |    32:??? "Let id:{rawBytesRead} be a List of length id:{elementSize} whose elements are the sequence of id:{elementSize} bytes starting with id:{block} [ id:{indexedPosition} ] ."
  |    33:app __x11__ = (ByteListEqual rawBytesRead expectedBytes)
  |    33:if (= __x11__ true) ??? "Store the individual bytes of id:{replacementBytes} into id:{block} , starting at id:{block} [ id:{indexedPosition} ] ." else 22:{}
  |  }
  |  35:app __x12__ = (RawBytesToNumeric elementType rawBytesRead isLittleEndian)
  |  35:return __x12__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _buffer_ be ? ValidateIntegerTypedArray(_typedArray_).""",
    """        1. Let _block_ be _buffer_.[[ArrayBufferData]].""",
    """        1. Let _indexedPosition_ be ? ValidateAtomicAccess(_typedArray_, _index_).""",
    """        1. Let _arrayTypeName_ be _typedArray_.[[TypedArrayName]].""",
    """        1. If _typedArray_.[[ContentType]] is ~BigInt~, then""",
    """          1. Let _expected_ be ? ToBigInt(_expectedValue_).""",
    """          1. Let _replacement_ be ? ToBigInt(_replacementValue_).""",
    """        1. Else,""",
    """          1. Let _expected_ be 𝔽(? ToIntegerOrInfinity(_expectedValue_)).""",
    """          1. Let _replacement_ be 𝔽(? ToIntegerOrInfinity(_replacementValue_)).""",
    """        1. If IsDetachedBuffer(_buffer_) is *true*, throw a *TypeError* exception.""",
    """        1. NOTE: The above check is not redundant with the check in ValidateIntegerTypedArray because the call to ToBigInt or ToIntegerOrInfinity on the preceding lines can have arbitrary side effects, which could cause the buffer to become detached.""",
    """        1. Let _elementType_ be the Element Type value in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for _arrayTypeName_.""",
    """        1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _elementType_.""",
    """        1. Let _isLittleEndian_ be the value of the [[LittleEndian]] field of the surrounding agent's Agent Record.""",
    """        1. Let _expectedBytes_ be NumericToRawBytes(_elementType_, _expected_, _isLittleEndian_).""",
    """        1. Let _replacementBytes_ be NumericToRawBytes(_elementType_, _replacement_, _isLittleEndian_).""",
    """        1. If IsSharedArrayBuffer(_buffer_) is *true*, then""",
    """          1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """          1. Let _eventList_ be the [[EventList]] field of the element in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """          1. Let _rawBytesRead_ be a List of length _elementSize_ whose elements are nondeterministically chosen byte values.""",
    """          1. NOTE: In implementations, _rawBytesRead_ is the result of a load-link, of a load-exclusive, or of an operand of a read-modify-write instruction on the underlying hardware. The nondeterminism is a semantic prescription of the memory model to describe observable behaviour of hardware with weak consistency.""",
    """          1. NOTE: The comparison of the expected value and the read value is performed outside of the read-modify-write modification function to avoid needlessly strong synchronization when the expected value is not equal to the read value.""",
    """          1. If ByteListEqual(_rawBytesRead_, _expectedBytes_) is *true*, then""",
    """            1. Let _second_ be a new read-modify-write modification function with parameters (_oldBytes_, _newBytes_) that captures nothing and performs the following steps atomically when called:""",
    """              1. Return _newBytes_.""",
    """            1. Let _event_ be ReadModifyWriteSharedMemory { [[Order]]: ~SeqCst~, [[NoTear]]: *true*, [[Block]]: _block_, [[ByteIndex]]: _indexedPosition_, [[ElementSize]]: _elementSize_, [[Payload]]: _replacementBytes_, [[ModifyOp]]: _second_ }.""",
    """          1. Else,""",
    """            1. Let _event_ be ReadSharedMemory { [[Order]]: ~SeqCst~, [[NoTear]]: *true*, [[Block]]: _block_, [[ByteIndex]]: _indexedPosition_, [[ElementSize]]: _elementSize_ }.""",
    """          1. Append _event_ to _eventList_.""",
    """          1. Append Chosen Value Record { [[Event]]: _event_, [[ChosenValue]]: _rawBytesRead_ } to _execution_.[[ChosenValues]].""",
    """        1. Else,""",
    """          1. Let _rawBytesRead_ be a List of length _elementSize_ whose elements are the sequence of _elementSize_ bytes starting with _block_[_indexedPosition_].""",
    """          1. If ByteListEqual(_rawBytesRead_, _expectedBytes_) is *true*, then""",
    """            1. Store the individual bytes of _replacementBytes_ into _block_, starting at _block_[_indexedPosition_].""",
    """        1. Return RawBytesToNumeric(_elementType_, _rawBytesRead_, _isLittleEndian_).""",
  )
}
