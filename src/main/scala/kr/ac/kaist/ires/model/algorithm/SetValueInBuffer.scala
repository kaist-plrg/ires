package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SetValueInBuffer` extends Algo {
  val head = NormalHead("SetValueInBuffer", List(Param("arrayBuffer", Normal), Param("byteIndex", Normal), Param("type", Normal), Param("value", Normal), Param("isTypedArray", Normal), Param("order", Normal), Param("isLittleEndian", Optional)))
  val ids = List(
    "sec-setvalueinbuffer",
    "sec-abstract-operations-for-arraybuffer-objects",
    "sec-arraybuffer-objects",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsDetachedBuffer arrayBuffer)
  |  0:assert (= __x0__ false)
  |  3:let block = arrayBuffer.ArrayBufferData
  |  4:??? "Let id:{elementSize} be the Element Size value specified in link:{unhandled: table-the-typedarray-constructors} for Element Type id:{type} ."
  |  5:if (= isLittleEndian absent) isLittleEndian = AGENT.LittleEndian else 2:{}
  |  6:app __x1__ = (NumericToRawBytes type value isLittleEndian)
  |  6:let rawBytes = __x1__
  |  7:app __x2__ = (IsSharedArrayBuffer arrayBuffer)
  |  7:if (= __x2__ true) {
  |    8:let execution = AGENT.CandidateExecution
  |    9:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |    10:let __x3__ = true
  |    10:__x3__ = (= isTypedArray true)
  |    10:if __x3__ {
  |      app __x4__ = (IsNoTearConfiguration type order)
  |      __x3__ = (= __x4__ true)
  |    } else 2:{}
  |    10:if __x3__ let noTear = true else let noTear = false
  |    11:append (new WriteSharedMemory("Order" -> order, "NoTear" -> noTear, "Block" -> block, "ByteIndex" -> byteIndex, "ElementSize" -> elementSize, "Payload" -> rawBytes)) -> eventList
  |  } else 2:{}
  |  12:??? "Else , store the individual bytes of id:{rawBytes} into id:{block} , starting at id:{block} [ id:{byteIndex} ] ."
  |  13:return undefined
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
    """            1. If _isTypedArray_ is *true* and IsNoTearConfiguration(_type_, _order_) is *true*, let _noTear_ be *true*; otherwise let _noTear_ be *false*.""",
    """            1. Append WriteSharedMemory { [[Order]]: _order_, [[NoTear]]: _noTear_, [[Block]]: _block_, [[ByteIndex]]: _byteIndex_, [[ElementSize]]: _elementSize_, [[Payload]]: _rawBytes_ } to _eventList_.""",
    """          1. Else, store the individual bytes of _rawBytes_ into _block_, starting at _block_[_byteIndex_].""",
    """          1. Return NormalCompletion(*undefined*).""",
  )
}
