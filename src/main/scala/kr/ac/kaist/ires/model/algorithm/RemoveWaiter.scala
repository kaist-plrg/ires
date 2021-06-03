package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RemoveWaiter` extends Algo {
  val head = NormalHead("RemoveWaiter", List(Param("WL", Normal), Param("W", Normal)))
  val ids = List(
    "sec-removewaiter",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  2:let __x0__ = 0i
  |  2:while (< __x0__ WL.length) if (= WL[__x0__] W) (pop WL __x0__) else __x0__ = (+ __x0__ 1i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Assert: _W_ is on the list of waiters in _WL_.""",
    """          1. Remove _W_ from the list of waiters in _WL_.""",
  )
}
