package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PerformPromiseRace` extends Algo {
  val head = NormalHead("PerformPromiseRace", List(Param("iteratorRecord", Normal), Param("constructor", Normal), Param("resultCapability", Normal), Param("promiseResolve", Normal)))
  val ids = List(
    "sec-performpromiserace",
    "sec-promise.race",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor constructor)
  |  0:assert (= __x0__ true)
  |  1:app __x1__ = (IsCallable promiseResolve)
  |  1:assert (= __x1__ true)
  |  2:while true {
  |    3:app __x2__ = (IteratorStep iteratorRecord)
  |    3:let next = __x2__
  |    4:app __x3__ = (IsAbruptCompletion next)
  |    4:if __x3__ iteratorRecord.Done = true else 10:{}
  |    5:[? next]
  |    6:if (= next false) {
  |      7:iteratorRecord.Done = true
  |      8:return resultCapability.Promise
  |    } else 10:{}
  |    9:app __x4__ = (IteratorValue next)
  |    9:let nextValue = __x4__
  |    10:app __x5__ = (IsAbruptCompletion nextValue)
  |    10:if __x5__ iteratorRecord.Done = true else 10:{}
  |    11:[? nextValue]
  |    12:app __x6__ = (Call promiseResolve constructor (new [nextValue]))
  |    12:let nextPromise = [? __x6__]
  |    13:app __x7__ = (Invoke nextPromise "then" (new [resultCapability.Resolve, resultCapability.Reject]))
  |    13:[? __x7__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: IsConstructor(_constructor_) is *true*.""",
    """            1. Assert: IsCallable(_promiseResolve_) is *true*.""",
    """            1. Repeat,""",
    """              1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """              1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_next_).""",
    """              1. If _next_ is *false*, then""",
    """                1. Set _iteratorRecord_.[[Done]] to *true*.""",
    """                1. Return _resultCapability_.[[Promise]].""",
    """              1. Let _nextValue_ be IteratorValue(_next_).""",
    """              1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_nextValue_).""",
    """              1. Let _nextPromise_ be ? Call(_promiseResolve_, _constructor_, « _nextValue_ »).""",
    """              1. Perform ? Invoke(_nextPromise_, *"then"*, « _resultCapability_.[[Resolve]], _resultCapability_.[[Reject]] »).""",
  )
}
