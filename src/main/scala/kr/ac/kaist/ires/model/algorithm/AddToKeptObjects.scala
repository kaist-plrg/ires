package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AddToKeptObjects` extends Algo {
  val head = NormalHead("AddToKeptObjects", List(Param("object", Normal)))
  val ids = List(
    "sec-addtokeptobjects",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:let agentRecord = AGENT
  |  1:append object -> agentRecord.KeptAlive
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. Let _agentRecord_ be the surrounding agent's Agent Record.""",
    """      1. Append _object_ to _agentRecord_.[[KeptAlive]].""",
  )
}
