package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TearFreeReads` extends Algo {
  val head = NormalHead("TearFreeReads", List())
  val ids = List(
    "sec-tear-free-aligned-reads",
    "sec-properties-of-valid-executions",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (SharedDataBlockEventSet execution)
  |  0:let __x1__ = __x0__
  |  0:let __x2__ = 0i
  |  0:while (< __x2__ __x1__.length) {
  |    let R = __x1__[__x2__]
  |    1:if (= R.NoTear true) {
  |      2:assert (= (% R.ByteIndex R.ElementSize) 0i)
  |      3:??? "For each event id:{W} such that ( id:{R} , id:{W} ) is in id:{execution} . [ [ ReadsFrom ] ] and id:{W} . [ [ NoTear ] ] is value:{true} , do in:{} out:{}"
  |    } else 2:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |  6:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. For each ReadSharedMemory or ReadModifyWriteSharedMemory event _R_ of SharedDataBlockEventSet(_execution_), do""",
    """          1. If _R_.[[NoTear]] is *true*, then""",
    """            1. Assert: The remainder of dividing _R_.[[ByteIndex]] by _R_.[[ElementSize]] is 0.""",
    """            1. For each event _W_ such that (_R_, _W_) is in _execution_.[[ReadsFrom]] and _W_.[[NoTear]] is *true*, do""",
    """              1. If _R_ and _W_ have equal ranges, and there is an event _V_ such that _V_ and _W_ have equal ranges, _V_.[[NoTear]] is *true*, _W_ is not _V_, and (_R_, _V_) is in _execution_.[[ReadsFrom]], then""",
    """                1. Return *false*.""",
    """        1. Return *true*.""",
  )
}
