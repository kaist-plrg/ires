package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CreateSharedByteDataBlock` extends Algo {
  val head = NormalHead("CreateSharedByteDataBlock", List(Param("size", Normal)))
  val ids = List(
    "sec-createsharedbytedatablock",
    "sec-data-blocks",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:assert (! (< size 0i))
  |  1:??? "Let id:{db} be a new Shared Data Block value consisting of id:{size} bytes . If it is impossible to create such a Shared Data Block , throw a value:{RangeError} exception ."
  |  2:let execution = AGENT.CandidateExecution
  |  3:??? "Let id:{eventList} be the [ [ EventList ] ] field of the element in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |  4:let zero = (new [0i])
  |  5:let __x0__ = db
  |  5:let __x1__ = 0i
  |  5:while (< __x1__ __x0__.length) {
  |    let i = __x0__[__x1__]
  |    6:append (new WriteSharedMemory("Order" -> CONST_Init, "NoTear" -> true, "Block" -> db, "ByteIndex" -> i, "ElementSize" -> 1i, "Payload" -> zero)) -> eventList
  |    __x1__ = (+ __x1__ 1i)
  |  }
  |  7:return db
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _size_ ≥ 0.""",
    """          1. Let _db_ be a new Shared Data Block value consisting of _size_ bytes. If it is impossible to create such a Shared Data Block, throw a *RangeError* exception.""",
    """          1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """          1. Let _eventList_ be the [[EventList]] field of the element in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """          1. Let _zero_ be « 0 ».""",
    """          1. For each index _i_ of _db_, do""",
    """            1. Append WriteSharedMemory { [[Order]]: ~Init~, [[NoTear]]: *true*, [[Block]]: _db_, [[ByteIndex]]: _i_, [[ElementSize]]: 1, [[Payload]]: _zero_ } to _eventList_.""",
    """          1. Return _db_.""",
  )
}
