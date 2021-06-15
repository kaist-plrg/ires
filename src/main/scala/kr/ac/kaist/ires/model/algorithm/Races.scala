package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Races` extends Algo {
  val head = NormalHead("Races", List())
  val ids = List(
    "sec-races",
    "sec-memory-model",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= E D)) ??? "If the pairs ( id:{E} , id:{D} ) and ( id:{D} , id:{E} ) are not in id:{execution} . [ [ HappensBefore ] ] , then in:{} out:{}" else 2:{}
  |  6:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. If _E_ is not _D_, then""",
    """        1. If the pairs (_E_, _D_) and (_D_, _E_) are not in _execution_.[[HappensBefore]], then""",
    """          1. If _E_ and _D_ are both WriteSharedMemory or ReadModifyWriteSharedMemory events and _E_ and _D_ do not have disjoint ranges, then""",
    """            1. Return *true*.""",
    """          1. If either (_E_, _D_) or (_D_, _E_) is in _execution_.[[ReadsFrom]], then""",
    """            1. Return *true*.""",
    """      1. Return *false*.""",
  )
}
