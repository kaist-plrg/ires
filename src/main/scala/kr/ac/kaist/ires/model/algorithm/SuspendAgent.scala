package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SuspendAgent` extends Algo {
  val head = NormalHead("SuspendAgent", List(Param("WL", Normal), Param("W", Normal), Param("timeout", Normal)))
  val ids = List(
    "sec-suspendagent",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  3:app __x0__ = (AgentCanSuspend)
  |  3:assert (= __x0__ true)
  |  4:??? "Perform LeaveCriticalSection ( id:{WL} ) and suspend id:{W} for up to id:{timeout} milliseconds , performing the combined operation in such a way that a notification that arrives after the critical section is exited but before the suspension takes effect is not lost . id:{W} can notify either because the timeout expired or because it was notified explicitly by another agent calling NotifyWaiter ( id:{WL} , id:{W} ) , and not for any other reasons at all ."
  |  5:app __x1__ = (EnterCriticalSection WL)
  |  5:__x1__
  |  6:??? "If id:{W} was notified explicitly by another agent calling NotifyWaiter ( id:{WL} , id:{W} ) , return value:{true} ."
  |  7:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Assert: _W_ is equivalent to AgentSignifier().""",
    """          1. Assert: _W_ is on the list of waiters in _WL_.""",
    """          1. Assert: AgentCanSuspend() is *true*.""",
    """          1. Perform LeaveCriticalSection(_WL_) and suspend _W_ for up to _timeout_ milliseconds, performing the combined operation in such a way that a notification that arrives after the critical section is exited but before the suspension takes effect is not lost. _W_ can notify either because the timeout expired or because it was notified explicitly by another agent calling NotifyWaiter(_WL_, _W_), and not for any other reasons at all.""",
    """          1. Perform EnterCriticalSection(_WL_).""",
    """          1. If _W_ was notified explicitly by another agent calling NotifyWaiter(_WL_, _W_), return *true*.""",
    """          1. Return *false*.""",
  )
}
