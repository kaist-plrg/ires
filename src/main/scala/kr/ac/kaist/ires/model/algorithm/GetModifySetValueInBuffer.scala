package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetModifySetValueInBuffer` extends Algo {
  val head = NormalHead("GetModifySetValueInBuffer", List(Param("arrayBuffer", Normal), Param("byteIndex", Normal), Param("type", Normal), Param("value", Normal), Param("op", Normal), Param("isLittleEndian", Optional)))
  val ids = List(
    "sec-getmodifysetvalueinbuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsDetachedBuffer arrayBuffer)
  |  0:assert (= __x0__ false)
  |  3:let block = arrayBuffer.ArrayBufferData
  |  4:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{type} ."
  |  5:if (= isLittleEndian absent) isLittleEndian = AGENT.LittleEndian else 11:{}
  |  6:app __x1__ = (NumericToRawBytes type value isLittleEndian)
  |  6:let rawBytes = __x1__
  |  15:app __x2__ = (IsSharedArrayBuffer arrayBuffer)
  |  15:if (= __x2__ true) {
  |    8:let execution = AGENT.CandidateExecution
  |    9:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |    10:??? "Let id:{rawBytesRead} be a List of length id:{elementSize} whose elements are nondeterministically chosen byte values ."
  |    12:let rmwEvent = (new ReadModifyWriteSharedMemory("Order" -> CONST_SeqCst, "NoTear" -> true, "Block" -> block, "ByteIndex" -> byteIndex, "ElementSize" -> elementSize, "Payload" -> rawBytes, "ModifyOp" -> op))
  |    13:append rmwEvent -> eventList
  |    14:append (new ChosenValueRecord("Event" -> rmwEvent, "ChosenValue" -> rawBytesRead)) -> execution.ChosenValues
  |  } else {
  |    16:??? "Let id:{rawBytesRead} be a List of length id:{elementSize} whose elements are the sequence of id:{elementSize} bytes starting with id:{block} [ id:{byteIndex} ] ."
  |    17:app __x3__ = (op rawBytesRead rawBytes)
  |    17:let rawBytesModified = __x3__
  |    18:??? "Store the individual bytes of id:{rawBytesModified} into id:{block} , starting at id:{block} [ id:{byteIndex} ] ."
  |  }
  |  19:app __x4__ = (RawBytesToNumeric type rawBytesRead isLittleEndian)
  |  19:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsDetachedBuffer(_arrayBuffer_) is *false*.""",
    """          1. Assert: There are sufficient bytes in _arrayBuffer_ starting at _byteIndex_ to represent a value of _type_.""",
    """          1. Assert: Type(_value_) is BigInt if ! IsBigIntElementType(_type_) is *true*; otherwise, Type(_value_) is Number.""",
    """          1. Let _block_ be _arrayBuffer_.[[ArrayBufferData]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _type_.""",
    """          1. If _isLittleEndian_ is not present, set _isLittleEndian_ to the value of the [[LittleEndian]] field of the surrounding agent's Agent Record.""",
    """          1. Let _rawBytes_ be NumericToRawBytes(_type_, _value_, _isLittleEndian_).""",
    """          1. If IsSharedArrayBuffer(_arrayBuffer_) is *true*, then""",
    """            1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """            1. Let _eventList_ be the [[EventList]] field of the element in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """            1. Let _rawBytesRead_ be a List of length _elementSize_ whose elements are nondeterministically chosen byte values.""",
    """            1. NOTE: In implementations, _rawBytesRead_ is the result of a load-link, of a load-exclusive, or of an operand of a read-modify-write instruction on the underlying hardware. The nondeterminism is a semantic prescription of the memory model to describe observable behaviour of hardware with weak consistency.""",
    """            1. Let _rmwEvent_ be ReadModifyWriteSharedMemory { [[Order]]: ~SeqCst~, [[NoTear]]: *true*, [[Block]]: _block_, [[ByteIndex]]: _byteIndex_, [[ElementSize]]: _elementSize_, [[Payload]]: _rawBytes_, [[ModifyOp]]: _op_ }.""",
    """            1. Append _rmwEvent_ to _eventList_.""",
    """            1. Append Chosen Value Record { [[Event]]: _rmwEvent_, [[ChosenValue]]: _rawBytesRead_ } to _execution_.[[ChosenValues]].""",
    """          1. Else,""",
    """            1. Let _rawBytesRead_ be a List of length _elementSize_ whose elements are the sequence of _elementSize_ bytes starting with _block_[_byteIndex_].""",
    """            1. Let _rawBytesModified_ be _op_(_rawBytesRead_, _rawBytes_).""",
    """            1. Store the individual bytes of _rawBytesModified_ into _block_, starting at _block_[_byteIndex_].""",
    """          1. Return RawBytesToNumeric(_type_, _rawBytesRead_, _isLittleEndian_).""",
  )
}
