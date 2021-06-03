package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Construct` extends Algo {
  val head = NormalHead("Construct", List(Param("F", Normal), Param("argumentsList", Optional), Param("newTarget", Optional)))
  val ids = List(
    "sec-construct",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= newTarget absent) newTarget = F else 4:{}
  |  1:if (= argumentsList absent) argumentsList = (new []) else 4:{}
  |  2:app __x0__ = (IsConstructor F)
  |  2:assert (= __x0__ true)
  |  3:app __x1__ = (IsConstructor newTarget)
  |  3:assert (= __x1__ true)
  |  4:app __x2__ = (F.Construct F argumentsList newTarget)
  |  4:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _newTarget_ is not present, set _newTarget_ to _F_.""",
    """        1. If _argumentsList_ is not present, set _argumentsList_ to a new empty List.""",
    """        1. Assert: IsConstructor(_F_) is *true*.""",
    """        1. Assert: IsConstructor(_newTarget_) is *true*.""",
    """        1. Return ? _F_.[[Construct]](_argumentsList_, _newTarget_).""",
  )
}
