package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LeaveCriticalSection` extends Algo {
  val head = NormalHead("LeaveCriticalSection", List(Param("WL", Normal)))
  val ids = List(
    "sec-leavecriticalsection",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:??? "Let id:{execution} be the [ [ CandidateExecution ] ] field of the calling surrounding ' s Agent Record ."
  |  2:??? "Let id:{eventsRecord} be the Agent Events Record in id:{execution} . [ [ EventsRecords ] ] whose [ [ AgentSignifier ] ] is AgentSignifier ( ) ."
  |  3:let leaverEventList = eventsRecord.EventList
  |  4:??? "Let id:{leaveEvent} be a new Synchronize event ."
  |  5:append leaveEvent -> leaverEventList
  |  6:??? "Set the Synchronize event in id:{WL} to id:{leaveEvent} ."
  |  7:??? "Leave the critical section for id:{WL} ."
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Let _execution_ be the [[CandidateExecution]] field of the calling surrounding's Agent Record.""",
    """          1. Let _eventsRecord_ be the Agent Events Record in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """          1. Let _leaverEventList_ be _eventsRecord_.[[EventList]].""",
    """          1. Let _leaveEvent_ be a new Synchronize event.""",
    """          1. Append _leaveEvent_ to _leaverEventList_.""",
    """          1. Set the Synchronize event in _WL_ to _leaveEvent_.""",
    """          1. Leave the critical section for _WL_.""",
  )
}
