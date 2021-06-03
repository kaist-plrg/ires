package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EnterCriticalSection` extends Algo {
  val head = NormalHead("EnterCriticalSection", List(Param("WL", Normal)))
  val ids = List(
    "sec-entercriticalsection",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  1:??? "Wait until no agent is in the critical section for id:{WL} , then enter the critical section for id:{WL} ( without allowing any other agent to enter ) ."
  |  2:??? "If id:{WL} has a Synchronize event , then in:{} out:{}"
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is not in the critical section for any WaiterList.""",
    """          1. Wait until no agent is in the critical section for _WL_, then enter the critical section for _WL_ (without allowing any other agent to enter).""",
    """          1. If _WL_ has a Synchronize event, then""",
    """            1. NOTE: A _WL_ whose critical section has been entered at least once has a Synchronize event set by LeaveCriticalSection.""",
    """            1. Let _execution_ be the [[CandidateExecution]] field of the surrounding agent's Agent Record.""",
    """            1. Let _eventsRecord_ be the Agent Events Record in _execution_.[[EventsRecords]] whose [[AgentSignifier]] is AgentSignifier().""",
    """            1. Let _entererEventList_ be _eventsRecord_.[[EventList]].""",
    """            1. Let _enterEvent_ be a new Synchronize event.""",
    """            1. Append _enterEvent_ to _entererEventList_.""",
    """            1. Let _leaveEvent_ be the Synchronize event in _WL_.""",
    """            1. Append (_leaveEvent_, _enterEvent_) to _eventsRecord_.[[AgentSynchronizesWith]].""",
  )
}
