package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CoherentReads` extends Algo {
  val head = NormalHead("CoherentReads", List())
  val ids = List(
    "sec-coherent-reads",
    "sec-properties-of-valid-executions",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (SharedDataBlockEventSet execution)
  |  0:let __x1__ = __x0__
  |  0:let __x2__ = 0i
  |  0:while (< __x2__ __x1__.length) {
  |    let R = __x1__[__x2__]
  |    1:app __x3__ = (execution.ReadsBytesFrom execution R)
  |    1:let Ws = __x3__
  |    2:let byteLocation = R.ByteIndex
  |    3:let __x4__ = Ws
  |    3:let __x5__ = 0i
  |    3:while (< __x5__ __x4__.length) {
  |      let W = __x4__[__x5__]
  |      4:if (contains execution.HappensBefore (new [R, W])) return false else 2:{}
  |      6:??? "If there is a WriteSharedMemory or ReadModifyWriteSharedMemory event id:{V} that has id:{byteLocation} in its range such that the pairs ( id:{W} , id:{V} ) and ( id:{V} , id:{R} ) are in id:{execution} . [ [ HappensBefore ] ] , then in:{} out:{}"
  |      8:byteLocation = (+ byteLocation 1i)
  |      __x5__ = (+ __x5__ 1i)
  |    }
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  9:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. For each ReadSharedMemory or ReadModifyWriteSharedMemory event _R_ of SharedDataBlockEventSet(_execution_), do""",
    """          1. Let _Ws_ be _execution_.[[ReadsBytesFrom]](_R_).""",
    """          1. Let _byteLocation_ be _R_.[[ByteIndex]].""",
    """          1. For each element _W_ of _Ws_, do""",
    """            1. If (_R_, _W_) is in _execution_.[[HappensBefore]], then""",
    """              1. Return *false*.""",
    """            1. If there is a WriteSharedMemory or ReadModifyWriteSharedMemory event _V_ that has _byteLocation_ in its range such that the pairs (_W_, _V_) and (_V_, _R_) are in _execution_.[[HappensBefore]], then""",
    """              1. Return *false*.""",
    """            1. Set _byteLocation_ to _byteLocation_ + 1.""",
    """        1. Return *true*.""",
  )
}
