package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetValueFromBuffer` extends Algo {
  val head = NormalHead("GetValueFromBuffer", List(Param("arrayBuffer", Normal), Param("byteIndex", Normal), Param("type", Normal), Param("isTypedArray", Normal), Param("order", Normal), Param("isLittleEndian", Optional)))
  val ids = List(
    "sec-getvaluefrombuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsDetachedBuffer arrayBuffer)
  |  0:assert (= __x0__ false)
  |  2:let block = arrayBuffer.ArrayBufferData
  |  3:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{type} ."
  |  4:app __x1__ = (IsSharedArrayBuffer arrayBuffer)
  |  4:if (= __x1__ true) {
  |    5:let execution = AGENT.CandidateExecution
  |    6:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |    7:let __x2__ = true
  |    7:__x2__ = (= isTypedArray true)
  |    7:if __x2__ {
  |      app __x3__ = (IsNoTearConfiguration type order)
  |      __x2__ = (= __x3__ true)
  |    } else 9:{}
  |    7:if __x2__ let noTear = true else let noTear = false
  |    8:let rawValue = NumList
  |    10:let readEvent = (new ReadSharedMemory("Order" -> order, "NoTear" -> noTear, "Block" -> block, "ByteIndex" -> byteIndex, "ElementSize" -> elementSize))
  |    11:append readEvent -> eventList
  |    12:append (new ChosenValueRecord("Event" -> readEvent, "ChosenValue" -> rawValue)) -> execution.ChosenValues
  |  } else 9:{}
  |  13:let rawValue = NumList
  |  14:assert (= rawValue.length elementSize)
  |  15:if (= isLittleEndian absent) isLittleEndian = AGENT.LittleEndian else 9:{}
  |  16:app __x4__ = (RawBytesToNumeric type rawValue isLittleEndian)
  |  16:return __x4__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: IsDetachedBuffer(_arrayBuffer_) is *false*.""",
    """          1. Assert: There are sufficient bytes in _arrayBuffer_ starting at _byteIndex_ to represent a value of _type_.""",
    """          1. Let _block_ be _arrayBuffer_.[[ArrayBufferData]].""",
    """          1. Let _elementSize_ be the Element Size value specified in <emu-xref href="#table-the-typedarray-constructors"></emu-xref> for Element Type _type_.""",
    """          1. If IsSharedArrayBuffer(_arrayBuffer_) is *true*, then""",
    """            1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """            1. Let _eventList_ be the [[EventList]] field of the element in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """            1. If _isTypedArray_ is *true* and IsNoTearConfiguration(_type_, _order_) is *true*, let _noTear_ be *true*; otherwise let _noTear_ be *false*.""",
    """            1. Let _rawValue_ be a List of length _elementSize_ whose elements are nondeterministically chosen byte values.""",
    """            1. NOTE: In implementations, _rawValue_ is the result of a non-atomic or atomic read instruction on the underlying hardware. The nondeterminism is a semantic prescription of the memory model to describe observable behaviour of hardware with weak consistency.""",
    """            1. Let _readEvent_ be ReadSharedMemory { [[Order]]: _order_, [[NoTear]]: _noTear_, [[Block]]: _block_, [[ByteIndex]]: _byteIndex_, [[ElementSize]]: _elementSize_ }.""",
    """            1. Append _readEvent_ to _eventList_.""",
    """            1. Append Chosen Value Record { [[Event]]: _readEvent_, [[ChosenValue]]: _rawValue_ } to _execution_.[[ChosenValues]].""",
    """          1. Else, let _rawValue_ be a List whose elements are bytes from _block_ at indices _byteIndex_ (inclusive) through _byteIndex_ + _elementSize_ (exclusive).""",
    """          1. Assert: The number of elements in _rawValue_ is _elementSize_.""",
    """          1. If _isLittleEndian_ is not present, set _isLittleEndian_ to the value of the [[LittleEndian]] field of the surrounding agent's Agent Record.""",
    """          1. Return RawBytesToNumeric(_type_, _rawValue_, _isLittleEndian_).""",
  )
}
