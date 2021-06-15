package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AddWaiter` extends Algo {
  val head = NormalHead("AddWaiter", List(Param("WL", Normal), Param("W", Normal)))
  val ids = List(
    "sec-addwaiter",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""append W -> WL""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Assert: _W_ is not on the list of waiters in any WaiterList.""",
    """          1. Add _W_ to the end of the list of waiters in _WL_.""",
  )
}
