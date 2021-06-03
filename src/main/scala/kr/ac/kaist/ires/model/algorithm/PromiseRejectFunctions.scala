package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PromiseRejectFunctions` extends Algo {
  val head = NormalHead("PromiseRejectFunctions", List())
  val ids = List(
    "sec-promise-reject-functions",
    "sec-createresolvingfunctions",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let F = CONTEXT.Function
  |  2:let promise = F.Promise
  |  3:let alreadyResolved = F.AlreadyResolved
  |  4:if (= alreadyResolved.Value true) return undefined else 1:{}
  |  5:alreadyResolved.Value = true
  |  6:app __x0__ = (RejectPromise promise reason)
  |  6:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Assert: _F_ has a [[Promise]] internal slot whose value is an Object.""",
    """            1. Let _promise_ be _F_.[[Promise]].""",
    """            1. Let _alreadyResolved_ be _F_.[[AlreadyResolved]].""",
    """            1. If _alreadyResolved_.[[Value]] is *true*, return *undefined*.""",
    """            1. Set _alreadyResolved_.[[Value]] to *true*.""",
    """            1. Return RejectPromise(_promise_, _reason_).""",
  )
}
