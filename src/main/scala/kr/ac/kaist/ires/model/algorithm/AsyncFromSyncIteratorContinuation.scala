package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFromSyncIteratorContinuation` extends Algo {
  val head = NormalHead("AsyncFromSyncIteratorContinuation", List(Param("result", Normal), Param("promiseCapability", Normal)))
  val ids = List(
    "sec-asyncfromsynciteratorcontinuation",
    "sec-async-from-sync-iterator-objects",
    "sec-iteration",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IteratorComplete result)
  |  0:let done = __x0__
  |  1:if (is-completion done) if (= done.Type CONST_normal) done = done.Value else {
  |    app __x1__ = (Call promiseCapability.Reject undefined (new [done.Value]))
  |    if (&& (is-completion __x1__) (! (= __x1__.Type CONST_normal))) return __x1__ else 7:{}
  |    return promiseCapability.Promise
  |  } else 7:{}
  |  1:done
  |  2:app __x2__ = (IteratorValue result)
  |  2:let value = __x2__
  |  3:if (is-completion value) if (= value.Type CONST_normal) value = value.Value else {
  |    app __x3__ = (Call promiseCapability.Reject undefined (new [value.Value]))
  |    if (&& (is-completion __x3__) (! (= __x3__.Type CONST_normal))) return __x3__ else 7:{}
  |    return promiseCapability.Promise
  |  } else 7:{}
  |  3:value
  |  4:app __x4__ = (PromiseResolve INTRINSIC_Promise value)
  |  4:let valueWrapper = __x4__
  |  5:if (is-completion valueWrapper) if (= valueWrapper.Type CONST_normal) valueWrapper = valueWrapper.Value else {
  |    app __x5__ = (Call promiseCapability.Reject undefined (new [valueWrapper.Value]))
  |    if (&& (is-completion __x5__) (! (= __x5__.Type CONST_normal))) return __x5__ else 7:{}
  |    return promiseCapability.Promise
  |  } else 7:{}
  |  5:valueWrapper
  |  6:??? "Let id:{steps} be the algorithm steps defined in link:{sec-async-from-sync-iterator-value-unwrap-functions} ."
  |  7:??? "Let id:{length} be the number of non - optional parameters of the function definition in link:{sec-async-from-sync-iterator-value-unwrap-functions} ."
  |  8:app __x6__ = (CreateBuiltinFunction steps length "" (new ["Done"]))
  |  8:let onFulfilled = [! __x6__]
  |  9:onFulfilled.Done = done
  |  10:app __x7__ = (PerformPromiseThen valueWrapper onFulfilled undefined promiseCapability)
  |  10:[! __x7__]
  |  11:return promiseCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _done_ be IteratorComplete(_result_).""",
    """          1. IfAbruptRejectPromise(_done_, _promiseCapability_).""",
    """          1. Let _value_ be IteratorValue(_result_).""",
    """          1. IfAbruptRejectPromise(_value_, _promiseCapability_).""",
    """          1. Let _valueWrapper_ be PromiseResolve(%Promise%, _value_).""",
    """          1. IfAbruptRejectPromise(_valueWrapper_, _promiseCapability_).""",
    """          1. Let _steps_ be the algorithm steps defined in <emu-xref href="#sec-async-from-sync-iterator-value-unwrap-functions" title></emu-xref>.""",
    """          1. Let _length_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-async-from-sync-iterator-value-unwrap-functions" title></emu-xref>.""",
    """          1. Let _onFulfilled_ be ! CreateBuiltinFunction(_steps_, _length_, *""*, « [[Done]] »).""",
    """          1. Set _onFulfilled_.[[Done]] to _done_.""",
    """          1. Perform ! PerformPromiseThen(_valueWrapper_, _onFulfilled_, *undefined*, _promiseCapability_).""",
    """          1. Return _promiseCapability_.[[Promise]].""",
  )
}
