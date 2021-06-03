package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostCallJobCallback` extends Algo {
  val head = NormalHead("HostCallJobCallback", List(Param("jobCallback", Normal), Param("V", Normal), Param("argumentsList", Normal)))
  val ids = List(
    "sec-hostcalljobcallback",
    "sec-jobs",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable jobCallback.Callback)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (Call jobCallback.Callback V argumentsList)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsCallable(_jobCallback_.[[Callback]]) is *true*.""",
    """        1. Return ? Call(_jobCallback_.[[Callback]], _V_, _argumentsList_).""",
  )
}
