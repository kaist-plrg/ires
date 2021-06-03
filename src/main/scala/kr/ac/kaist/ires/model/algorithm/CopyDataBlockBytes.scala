package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CopyDataBlockBytes` extends Algo {
  val head = NormalHead("CopyDataBlockBytes", List(Param("toBlock", Normal), Param("toIndex", Normal), Param("fromBlock", Normal), Param("fromIndex", Normal), Param("count", Normal)))
  val ids = List(
    "sec-copydatablockbytes",
    "sec-data-blocks",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  1:??? "Let id:{fromSize} be the number of bytes in id:{fromBlock} ."
  |  2:assert (! (< fromSize (+ fromIndex count)))
  |  3:??? "Let id:{toSize} be the number of bytes in id:{toBlock} ."
  |  4:assert (! (< toSize (+ toIndex count)))
  |  5:while (< 0i count) {
  |    18:if (is-instance-of fromBlock SharedDataBlock) {
  |      7:let execution = AGENT.CandidateExecution
  |      8:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |      9:??? "Let id:{bytes} be a List whose sole element is a nondeterministically chosen byte value ."
  |      11:let readEvent = (new ReadSharedMemory("Order" -> CONST_Unordered, "NoTear" -> true, "Block" -> fromBlock, "ByteIndex" -> fromIndex, "ElementSize" -> 1i))
  |      12:append readEvent -> eventList
  |      13:append (new ChosenValueRecord("Event" -> readEvent, "ChosenValue" -> bytes)) -> execution.ChosenValues
  |      16:if (is-instance-of toBlock SharedDataBlock) append (new WriteSharedMemory("Order" -> CONST_Unordered, "NoTear" -> true, "Block" -> toBlock, "ByteIndex" -> toIndex, "ElementSize" -> 1i, "Payload" -> bytes)) -> eventList else toBlock[toIndex] = bytes[0i]
  |    } else {
  |      19:assert (! (is-instance-of toBlock SharedDataBlock))
  |      20:toBlock[toIndex] = fromBlock[fromIndex]
  |    }
  |    21:toIndex = (+ toIndex 1i)
  |    22:fromIndex = (+ fromIndex 1i)
  |    23:count = (- count 1i)
  |  }
  |  24:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _fromBlock_ and _toBlock_ are distinct Data Block or Shared Data Block values.""",
    """          1. Let _fromSize_ be the number of bytes in _fromBlock_.""",
    """          1. Assert: _fromIndex_ + _count_ ≤ _fromSize_.""",
    """          1. Let _toSize_ be the number of bytes in _toBlock_.""",
    """          1. Assert: _toIndex_ + _count_ ≤ _toSize_.""",
    """          1. Repeat, while _count_ > 0,""",
    """            1. If _fromBlock_ is a Shared Data Block, then""",
    """              1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """              1. Let _eventList_ be the [[EventList]] field of the element in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """              1. Let _bytes_ be a List whose sole element is a nondeterministically chosen byte value.""",
    """              1. NOTE: In implementations, _bytes_ is the result of a non-atomic read instruction on the underlying hardware. The nondeterminism is a semantic prescription of the memory model to describe observable behaviour of hardware with weak consistency.""",
    """              1. Let _readEvent_ be ReadSharedMemory { [[Order]]: ~Unordered~, [[NoTear]]: *true*, [[Block]]: _fromBlock_, [[ByteIndex]]: _fromIndex_, [[ElementSize]]: 1 }.""",
    """              1. Append _readEvent_ to _eventList_.""",
    """              1. Append Chosen Value Record { [[Event]]: _readEvent_, [[ChosenValue]]: _bytes_ } to _execution_.[[ChosenValues]].""",
    """              1. If _toBlock_ is a Shared Data Block, then""",
    """                1. Append WriteSharedMemory { [[Order]]: ~Unordered~, [[NoTear]]: *true*, [[Block]]: _toBlock_, [[ByteIndex]]: _toIndex_, [[ElementSize]]: 1, [[Payload]]: _bytes_ } to _eventList_.""",
    """              1. Else,""",
    """                1. Set _toBlock_[_toIndex_] to _bytes_[0].""",
    """            1. Else,""",
    """              1. Assert: _toBlock_ is not a Shared Data Block.""",
    """              1. Set _toBlock_[_toIndex_] to _fromBlock_[_fromIndex_].""",
    """            1. Set _toIndex_ to _toIndex_ + 1.""",
    """            1. Set _fromIndex_ to _fromIndex_ + 1.""",
    """            1. Set _count_ to _count_ - 1.""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
