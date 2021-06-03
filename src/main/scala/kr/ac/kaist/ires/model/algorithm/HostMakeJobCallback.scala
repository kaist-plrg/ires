package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::HostMakeJobCallback` extends Algo {
  val head = NormalHead("HostMakeJobCallback", List(Param("callback", Normal)))
  val ids = List(
    "sec-hostmakejobcallback",
    "sec-jobs",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsCallable callback)
  |  0:assert (= __x0__ true)
  |  1:return (new JobCallbackRecord("Callback" -> callback, "HostDefined" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: IsCallable(_callback_) is *true*.""",
    """        1. Return the JobCallback Record { [[Callback]]: _callback_, [[HostDefined]]: ~empty~ }.""",
  )
}
