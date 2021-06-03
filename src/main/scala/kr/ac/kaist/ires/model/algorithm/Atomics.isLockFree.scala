package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Atomics.isLockFree` extends Algo {
  val head = BuiltinHead(parseRef("""Atomics.isLockFree"""), List(Param("size", Normal)))
  val ids = List(
    "sec-atomics.islockfree",
    "sec-atomics-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ToIntegerOrInfinity size)
  |  0:let n = [? __x0__]
  |  1:let AR = AGENT
  |  2:if (== n 1i) return AR.IsLockFree1 else 22:{}
  |  3:if (== n 2i) return AR.IsLockFree2 else 22:{}
  |  4:if (== n 4i) return true else 22:{}
  |  5:if (== n 8i) return AR.IsLockFree8 else 22:{}
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _n_ be ? ToIntegerOrInfinity(_size_).""",
    """        1. Let _AR_ be the Agent Record of the surrounding agent.""",
    """        1. If _n_ = 1, return _AR_.[[IsLockFree1]].""",
    """        1. If _n_ = 2, return _AR_.[[IsLockFree2]].""",
    """        1. If _n_ = 4, return *true*.""",
    """        1. If _n_ = 8, return _AR_.[[IsLockFree8]].""",
    """        1. Return *false*.""",
  )
}
