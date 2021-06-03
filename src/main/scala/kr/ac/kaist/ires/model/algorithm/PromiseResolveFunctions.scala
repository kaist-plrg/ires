package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PromiseResolveFunctions` extends Algo {
  val head = NormalHead("PromiseResolveFunctions", List())
  val ids = List(
    "sec-promise-resolve-functions",
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
  |  6:app __x0__ = (SameValue resolution promise)
  |  6:if (= __x0__ true) {
  |    7:let selfResolutionError = (new OrdinaryObject())
  |    8:app __x1__ = (RejectPromise promise selfResolutionError)
  |    8:return __x1__
  |  } else 1:{}
  |  9:if (! (= (typeof resolution) Object)) {
  |    10:app __x2__ = (FulfillPromise promise resolution)
  |    10:return __x2__
  |  } else 1:{}
  |  11:app __x3__ = (Get resolution "then")
  |  11:let then = __x3__
  |  12:app __x4__ = (IsAbruptCompletion then)
  |  12:if __x4__ {
  |    13:app __x5__ = (RejectPromise promise then.Value)
  |    13:return __x5__
  |  } else 1:{}
  |  14:let thenAction = then.Value
  |  15:app __x6__ = (IsCallable thenAction)
  |  15:if (= __x6__ false) {
  |    16:app __x7__ = (FulfillPromise promise resolution)
  |    16:return __x7__
  |  } else 1:{}
  |  17:app __x8__ = (HostMakeJobCallback thenAction)
  |  17:let thenJobCallback = __x8__
  |  18:app __x9__ = (NewPromiseResolveThenableJob promise resolution thenJobCallback)
  |  18:let job = __x9__
  |  19:app __x10__ = (HostEnqueuePromiseJob job.Job job.Realm)
  |  19:__x10__
  |  20:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Assert: _F_ has a [[Promise]] internal slot whose value is an Object.""",
    """            1. Let _promise_ be _F_.[[Promise]].""",
    """            1. Let _alreadyResolved_ be _F_.[[AlreadyResolved]].""",
    """            1. If _alreadyResolved_.[[Value]] is *true*, return *undefined*.""",
    """            1. Set _alreadyResolved_.[[Value]] to *true*.""",
    """            1. If SameValue(_resolution_, _promise_) is *true*, then""",
    """              1. Let _selfResolutionError_ be a newly created *TypeError* object.""",
    """              1. Return RejectPromise(_promise_, _selfResolutionError_).""",
    """            1. If Type(_resolution_) is not Object, then""",
    """              1. Return FulfillPromise(_promise_, _resolution_).""",
    """            1. Let _then_ be Get(_resolution_, *"then"*).""",
    """            1. If _then_ is an abrupt completion, then""",
    """              1. Return RejectPromise(_promise_, _then_.[[Value]]).""",
    """            1. Let _thenAction_ be _then_.[[Value]].""",
    """            1. If IsCallable(_thenAction_) is *false*, then""",
    """              1. Return FulfillPromise(_promise_, _resolution_).""",
    """            1. Let _thenJobCallback_ be HostMakeJobCallback(_thenAction_).""",
    """            1. Let _job_ be NewPromiseResolveThenableJob(_promise_, _resolution_, _thenJobCallback_).""",
    """            1. Perform HostEnqueuePromiseJob(_job_.[[Job]], _job_.[[Realm]]).""",
    """            1. Return *undefined*.""",
  )
}
