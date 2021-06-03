package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Promise.any` extends Algo {
  val head = BuiltinHead(parseRef("""Promise.any"""), List(Param("iterable", Normal)))
  val ids = List(
    "sec-promise.any",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let C = this
  |  1:app __x0__ = (NewPromiseCapability C)
  |  1:let promiseCapability = [? __x0__]
  |  2:app __x1__ = (GetPromiseResolve C)
  |  2:let promiseResolve = __x1__
  |  3:if (is-completion promiseResolve) if (= promiseResolve.Type CONST_normal) promiseResolve = promiseResolve.Value else {
  |    app __x2__ = (Call promiseCapability.Reject undefined (new [promiseResolve.Value]))
  |    if (&& (is-completion __x2__) (! (= __x2__.Type CONST_normal))) return __x2__ else 10:{}
  |    return promiseCapability.Promise
  |  } else 10:{}
  |  3:promiseResolve
  |  4:app __x3__ = (GetIterator iterable)
  |  4:let iteratorRecord = __x3__
  |  5:if (is-completion iteratorRecord) if (= iteratorRecord.Type CONST_normal) iteratorRecord = iteratorRecord.Value else {
  |    app __x4__ = (Call promiseCapability.Reject undefined (new [iteratorRecord.Value]))
  |    if (&& (is-completion __x4__) (! (= __x4__.Type CONST_normal))) return __x4__ else 10:{}
  |    return promiseCapability.Promise
  |  } else 10:{}
  |  5:iteratorRecord
  |  6:app __x5__ = (PerformPromiseAny iteratorRecord C promiseCapability promiseResolve)
  |  6:let result = __x5__
  |  7:app __x6__ = (IsAbruptCompletion result)
  |  7:if __x6__ {
  |    8:if (= iteratorRecord.Done false) {
  |      app __x7__ = (IteratorClose iteratorRecord result)
  |      result = __x7__
  |    } else 10:{}
  |    9:if (is-completion result) if (= result.Type CONST_normal) result = result.Value else {
  |      app __x8__ = (Call promiseCapability.Reject undefined (new [result.Value]))
  |      if (&& (is-completion __x8__) (! (= __x8__.Type CONST_normal))) return __x8__ else 10:{}
  |      return promiseCapability.Promise
  |    } else 10:{}
  |    9:result
  |  } else 10:{}
  |  10:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _C_ be the *this* value.""",
    """          1. Let _promiseCapability_ be ? NewPromiseCapability(_C_).""",
    """          1. Let _promiseResolve_ be GetPromiseResolve(_C_).""",
    """          1. IfAbruptRejectPromise(_promiseResolve_, _promiseCapability_).""",
    """          1. Let _iteratorRecord_ be GetIterator(_iterable_).""",
    """          1. IfAbruptRejectPromise(_iteratorRecord_, _promiseCapability_).""",
    """          1. Let _result_ be PerformPromiseAny(_iteratorRecord_, _C_, _promiseCapability_, _promiseResolve_).""",
    """          1. If _result_ is an abrupt completion, then""",
    """            1. If _iteratorRecord_.[[Done]] is *false*, set _result_ to IteratorClose(_iteratorRecord_, _result_).""",
    """            1. IfAbruptRejectPromise(_result_, _promiseCapability_).""",
    """          1. Return Completion(_result_).""",
  )
}
