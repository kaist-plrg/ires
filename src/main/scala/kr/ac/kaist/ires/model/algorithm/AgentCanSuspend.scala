package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AgentCanSuspend` extends Algo {
  val head = NormalHead("AgentCanSuspend", List())
  val ids = List(
    "sec-agentcansuspend",
    "sec-agents",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let AR = AGENT
  |  1:return AR.CanBlock
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _AR_ be the Agent Record of the surrounding agent.""",
    """        1. Return _AR_.[[CanBlock]].""",
  )
}
