package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ValueOfReadEvent` extends Algo {
  val head = NormalHead("ValueOfReadEvent", List(Param("execution", Normal), Param("R", Normal)))
  val ids = List(
    "sec-valueofreadevent",
    "sec-abstract-operations-for-the-memory-model",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:assert (|| (is-instance-of R ReadSharedMemory) (is-instance-of R ReadModifyWriteSharedMemory))
  |  1:app __x0__ = (execution.ReadsBytesFrom execution R)
  |  1:let Ws = __x0__
  |  3:app __x1__ = (ComposeWriteEventBytes execution R.ByteIndex Ws)
  |  3:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _R_ is a ReadSharedMemory or ReadModifyWriteSharedMemory event.""",
    """        1. Let _Ws_ be _execution_.[[ReadsBytesFrom]](_R_).""",
    """        1. Assert: _Ws_ is a List of WriteSharedMemory or ReadModifyWriteSharedMemory events with length equal to _R_.[[ElementSize]].""",
    """        1. Return ComposeWriteEventBytes(_execution_, _R_.[[ByteIndex]], _Ws_).""",
  )
}
