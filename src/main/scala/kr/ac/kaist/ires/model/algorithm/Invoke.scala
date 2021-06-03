package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Invoke` extends Algo {
  val head = NormalHead("Invoke", List(Param("V", Normal), Param("P", Normal), Param("argumentsList", Optional)))
  val ids = List(
    "sec-invoke",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPropertyKey P)
  |  0:assert (= __x0__ true)
  |  1:if (= argumentsList absent) argumentsList = (new []) else 0:{}
  |  2:app __x1__ = (GetV V P)
  |  2:let func = [? __x1__]
  |  3:app __x2__ = (Call func V argumentsList)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsPropertyKey(_P_) is *true*.""",
    """        1. If _argumentsList_ is not present, set _argumentsList_ to a new empty List.""",
    """        1. Let _func_ be ? GetV(_V_, _P_).""",
    """        1. Return ? Call(_func_, _V_, _argumentsList_).""",
  )
}
