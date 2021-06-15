package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::NotifyWaiter` extends Algo {
  val head = NormalHead("NotifyWaiter", List(Param("WL", Normal), Param("W", Normal)))
  val ids = List(
    "sec-notifywaiter",
    "sec-abstract-operations-for-atomics",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""??? "Notify the agent id:{W} ."""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The calling agent is in the critical section for _WL_.""",
    """          1. Notify the agent _W_.""",
  )
}
