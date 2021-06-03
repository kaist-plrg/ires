package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::RejectPromise` extends Algo {
  val head = NormalHead("RejectPromise", List(Param("promise", Normal), Param("reason", Normal)))
  val ids = List(
    "sec-rejectpromise",
    "sec-promise-abstract-operations",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:assert (= promise.PromiseState CONST_pending)
  |  1:let reactions = promise.PromiseRejectReactions
  |  2:promise.PromiseResult = reason
  |  3:promise.PromiseFulfillReactions = undefined
  |  4:promise.PromiseRejectReactions = undefined
  |  5:promise.PromiseState = CONST_rejected
  |  6:if (= promise.PromiseIsHandled false) {
  |    app __x0__ = (HostPromiseRejectionTracker promise "reject")
  |    __x0__
  |  } else 1:{}
  |  7:app __x1__ = (TriggerPromiseReactions reactions reason)
  |  7:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: The value of _promise_.[[PromiseState]] is ~pending~.""",
    """          1. Let _reactions_ be _promise_.[[PromiseRejectReactions]].""",
    """          1. Set _promise_.[[PromiseResult]] to _reason_.""",
    """          1. Set _promise_.[[PromiseFulfillReactions]] to *undefined*.""",
    """          1. Set _promise_.[[PromiseRejectReactions]] to *undefined*.""",
    """          1. Set _promise_.[[PromiseState]] to ~rejected~.""",
    """          1. If _promise_.[[PromiseIsHandled]] is *false*, perform HostPromiseRejectionTracker(_promise_, *"reject"*).""",
    """          1. Return TriggerPromiseReactions(_reactions_, _reason_).""",
  )
}
