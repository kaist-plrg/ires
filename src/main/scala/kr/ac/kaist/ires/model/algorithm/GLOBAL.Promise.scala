package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Promise` extends Algo {
  val head = BuiltinHead(parseRef("""Promise"""), List(Param("executor", Normal)))
  val ids = List(
    "sec-promise-executor",
    "sec-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 10:{}
  |  1:app __x0__ = (IsCallable executor)
  |  1:if (= __x0__ false) throw TypeError else 10:{}
  |  2:app __x1__ = (OrdinaryCreateFromConstructor NewTarget "%Promise.prototype%" (new ["PromiseState", "PromiseResult", "PromiseFulfillReactions", "PromiseRejectReactions", "PromiseIsHandled"]))
  |  2:let promise = [? __x1__]
  |  3:promise.PromiseState = CONST_pending
  |  4:promise.PromiseFulfillReactions = (new [])
  |  5:promise.PromiseRejectReactions = (new [])
  |  6:promise.PromiseIsHandled = false
  |  7:app __x2__ = (CreateResolvingFunctions promise)
  |  7:let resolvingFunctions = __x2__
  |  8:app __x3__ = (Call executor undefined (new [resolvingFunctions.Resolve, resolvingFunctions.Reject]))
  |  8:let completion = __x3__
  |  9:app __x4__ = (IsAbruptCompletion completion)
  |  9:if __x4__ {
  |    10:app __x5__ = (Call resolvingFunctions.Reject undefined (new [completion.Value]))
  |    10:[? __x5__]
  |  } else 10:{}
  |  11:return promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. If IsCallable(_executor_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _promise_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Promise.prototype%"*, « [[PromiseState]], [[PromiseResult]], [[PromiseFulfillReactions]], [[PromiseRejectReactions]], [[PromiseIsHandled]] »).""",
    """          1. Set _promise_.[[PromiseState]] to ~pending~.""",
    """          1. Set _promise_.[[PromiseFulfillReactions]] to a new empty List.""",
    """          1. Set _promise_.[[PromiseRejectReactions]] to a new empty List.""",
    """          1. Set _promise_.[[PromiseIsHandled]] to *false*.""",
    """          1. Let _resolvingFunctions_ be CreateResolvingFunctions(_promise_).""",
    """          1. Let _completion_ be Call(_executor_, *undefined*, « _resolvingFunctions_.[[Resolve]], _resolvingFunctions_.[[Reject]] »).""",
    """          1. If _completion_ is an abrupt completion, then""",
    """            1. Perform ? Call(_resolvingFunctions_.[[Reject]], *undefined*, « _completion_.[[Value]] »).""",
    """          1. Return _promise_.""",
  )
}
