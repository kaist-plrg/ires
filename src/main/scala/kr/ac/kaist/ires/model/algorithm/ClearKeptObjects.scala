package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClearKeptObjects` extends Algo {
  val head = NormalHead("ClearKeptObjects", List())
  val ids = List(
    "sec-clear-kept-objects",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let agentRecord = AGENT
  |  1:agentRecord.KeptAlive = (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. Let _agentRecord_ be the surrounding agent's Agent Record.""",
    """      1. Set _agentRecord_.[[KeptAlive]] to a new empty List.""",
  )
}
