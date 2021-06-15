package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CleanupFinalizationRegistry` extends Algo {
  val head = NormalHead("CleanupFinalizationRegistry", List(Param("finalizationRegistry", Normal)))
  val ids = List(
    "sec-cleanup-finalization-registry",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  1:let callback = finalizationRegistry.CleanupCallback
  |  2:??? "While id:{finalizationRegistry} . [ [ Cells ] ] contains a Record id:{cell} such that id:{cell} . [ [ WeakRefTarget ] ] is const:{empty} , an implementation may perform the following steps : in:{} out:{}"
  |  6:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. Assert: _finalizationRegistry_ has [[Cells]] and [[CleanupCallback]] internal slots.""",
    """      1. Let _callback_ be _finalizationRegistry_.[[CleanupCallback]].""",
    """      1. While _finalizationRegistry_.[[Cells]] contains a Record _cell_ such that _cell_.[[WeakRefTarget]] is ~empty~, an implementation may perform the following steps:""",
    """        1. Choose any such _cell_.""",
    """        1. Remove _cell_ from _finalizationRegistry_.[[Cells]].""",
    """        1. Perform ? Call(_callback_, *undefined*, « _cell_.[[HeldValue]] »).""",
    """      1. Return NormalCompletion(*undefined*).""",
  )
}
