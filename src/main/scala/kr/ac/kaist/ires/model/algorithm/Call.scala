package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Call` extends Algo {
  val head = NormalHead("Call", List(Param("F", Normal), Param("V", Normal), Param("argumentsList", Optional)))
  val ids = List(
    "sec-call",
    "sec-operations-on-objects",
    "sec-abstract-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (= argumentsList absent) argumentsList = (new []) else 4:{}
  |  1:app __x0__ = (IsCallable F)
  |  1:if (= __x0__ false) throw TypeError else 4:{}
  |  2:app __x1__ = (F.Call F V argumentsList)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _argumentsList_ is not present, set _argumentsList_ to a new empty List.""",
    """        1. If IsCallable(_F_) is *false*, throw a *TypeError* exception.""",
    """        1. Return ? _F_.[[Call]](_V_, _argumentsList_).""",
  )
}
