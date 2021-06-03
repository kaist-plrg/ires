package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PerformPromiseThen` extends Algo {
  val head = NormalHead("PerformPromiseThen", List(Param("promise", Normal), Param("onFulfilled", Normal), Param("onRejected", Normal), Param("resultCapability", Optional)))
  val ids = List(
    "sec-performpromisethen",
    "sec-promise.prototype.then",
    "sec-properties-of-the-promise-prototype-object",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsPromise promise)
  |  0:assert (= __x0__ true)
  |  1:if (= resultCapability absent) resultCapability = undefined else 10:{}
  |  5:app __x1__ = (IsCallable onFulfilled)
  |  5:if (= __x1__ false) let onFulfilledJobCallback = CONST_empty else {
  |    6:app __x2__ = (HostMakeJobCallback onFulfilled)
  |    6:let onFulfilledJobCallback = __x2__
  |  }
  |  9:app __x3__ = (IsCallable onRejected)
  |  9:if (= __x3__ false) let onRejectedJobCallback = CONST_empty else {
  |    10:app __x4__ = (HostMakeJobCallback onRejected)
  |    10:let onRejectedJobCallback = __x4__
  |  }
  |  11:let fulfillReaction = (new PromiseReaction("Capability" -> resultCapability, "Type" -> CONST_Fulfill, "Handler" -> onFulfilledJobCallback))
  |  12:let rejectReaction = (new PromiseReaction("Capability" -> resultCapability, "Type" -> CONST_Reject, "Handler" -> onRejectedJobCallback))
  |  20:if (= promise.PromiseState CONST_pending) {
  |    14:append fulfillReaction -> promise.PromiseFulfillReactions
  |    15:append rejectReaction -> promise.PromiseRejectReactions
  |  } else if (= promise.PromiseState CONST_fulfilled) {
  |    17:let value = promise.PromiseResult
  |    18:app __x5__ = (NewPromiseReactionJob fulfillReaction value)
  |    18:let fulfillJob = __x5__
  |    19:app __x6__ = (HostEnqueuePromiseJob fulfillJob.Job fulfillJob.Realm)
  |    19:__x6__
  |  } else {
  |    21:assert (= promise.PromiseState CONST_rejected)
  |    22:let reason = promise.PromiseResult
  |    23:if (= promise.PromiseIsHandled false) {
  |      app __x7__ = (HostPromiseRejectionTracker promise "handle")
  |      __x7__
  |    } else 10:{}
  |    24:app __x8__ = (NewPromiseReactionJob rejectReaction reason)
  |    24:let rejectJob = __x8__
  |    25:app __x9__ = (HostEnqueuePromiseJob rejectJob.Job rejectJob.Realm)
  |    25:__x9__
  |  }
  |  26:promise.PromiseIsHandled = true
  |  29:if (= resultCapability undefined) return undefined else return resultCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: IsPromise(_promise_) is *true*.""",
    """            1. If _resultCapability_ is not present, then""",
    """              1. Set _resultCapability_ to *undefined*.""",
    """            1. If IsCallable(_onFulfilled_) is *false*, then""",
    """              1. Let _onFulfilledJobCallback_ be ~empty~.""",
    """            1. Else,""",
    """              1. Let _onFulfilledJobCallback_ be HostMakeJobCallback(_onFulfilled_).""",
    """            1. If IsCallable(_onRejected_) is *false*, then""",
    """              1. Let _onRejectedJobCallback_ be ~empty~.""",
    """            1. Else,""",
    """              1. Let _onRejectedJobCallback_ be HostMakeJobCallback(_onRejected_).""",
    """            1. Let _fulfillReaction_ be the PromiseReaction { [[Capability]]: _resultCapability_, [[Type]]: ~Fulfill~, [[Handler]]: _onFulfilledJobCallback_ }.""",
    """            1. Let _rejectReaction_ be the PromiseReaction { [[Capability]]: _resultCapability_, [[Type]]: ~Reject~, [[Handler]]: _onRejectedJobCallback_ }.""",
    """            1. If _promise_.[[PromiseState]] is ~pending~, then""",
    """              1. Append _fulfillReaction_ as the last element of the List that is _promise_.[[PromiseFulfillReactions]].""",
    """              1. Append _rejectReaction_ as the last element of the List that is _promise_.[[PromiseRejectReactions]].""",
    """            1. Else if _promise_.[[PromiseState]] is ~fulfilled~, then""",
    """              1. Let _value_ be _promise_.[[PromiseResult]].""",
    """              1. Let _fulfillJob_ be NewPromiseReactionJob(_fulfillReaction_, _value_).""",
    """              1. Perform HostEnqueuePromiseJob(_fulfillJob_.[[Job]], _fulfillJob_.[[Realm]]).""",
    """            1. Else,""",
    """              1. Assert: The value of _promise_.[[PromiseState]] is ~rejected~.""",
    """              1. Let _reason_ be _promise_.[[PromiseResult]].""",
    """              1. If _promise_.[[PromiseIsHandled]] is *false*, perform HostPromiseRejectionTracker(_promise_, *"handle"*).""",
    """              1. Let _rejectJob_ be NewPromiseReactionJob(_rejectReaction_, _reason_).""",
    """              1. Perform HostEnqueuePromiseJob(_rejectJob_.[[Job]], _rejectJob_.[[Realm]]).""",
    """            1. Set _promise_.[[PromiseIsHandled]] to *true*.""",
    """            1. If _resultCapability_ is *undefined*, then""",
    """              1. Return *undefined*.""",
    """            1. Else,""",
    """              1. Return _resultCapability_.[[Promise]].""",
  )
}
