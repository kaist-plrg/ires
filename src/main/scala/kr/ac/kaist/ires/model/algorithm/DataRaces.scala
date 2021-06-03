package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DataRaces` extends Algo {
  val head = NormalHead("DataRaces", List())
  val ids = List(
    "sec-data-races",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:??? "If id:{E} and id:{D} are in a race in id:{execution} , then in:{} out:{}"
  |  5:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. If _E_ and _D_ are in a race in _execution_, then""",
    """        1. If _E_.[[Order]] is not ~SeqCst~ or _D_.[[Order]] is not ~SeqCst~, then""",
    """          1. Return *true*.""",
    """        1. If _E_ and _D_ have overlapping ranges, then""",
    """          1. Return *true*.""",
    """      1. Return *false*.""",
  )
}
