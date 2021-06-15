package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PerformPromiseAny` extends Algo {
  val head = NormalHead("PerformPromiseAny", List(Param("iteratorRecord", Normal), Param("constructor", Normal), Param("resultCapability", Normal), Param("promiseResolve", Normal)))
  val ids = List(
    "sec-performpromiseany",
    "sec-promise.any",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor constructor)
  |  0:assert (= [! __x0__] true)
  |  1:app __x1__ = (IsCallable promiseResolve)
  |  1:assert (= [! __x1__] true)
  |  2:let errors = (new [])
  |  3:let remainingElementsCount = (new Record("Value" -> 1i))
  |  4:let index = 0i
  |  5:while true {
  |    6:app __x2__ = (IteratorStep iteratorRecord)
  |    6:let next = __x2__
  |    7:app __x3__ = (IsAbruptCompletion next)
  |    7:if __x3__ iteratorRecord.Done = true else 10:{}
  |    8:[? next]
  |    9:if (= next false) {
  |      10:iteratorRecord.Done = true
  |      11:remainingElementsCount.Value = (- remainingElementsCount.Value 1i)
  |      12:if (= remainingElementsCount.Value 0i) {
  |        13:??? "Let id:{error} be a newly created code:{AggregateError} object ."
  |        14:app __x4__ = (CreateArrayFromList errors)
  |        14:app __x5__ = (DefinePropertyOrThrow error "errors" (new PropertyDescriptor("Configurable" -> true, "Enumerable" -> false, "Writable" -> true, "Value" -> [! __x4__])))
  |        14:[! __x5__]
  |        15:app __x6__ = (ThrowCompletion error)
  |        15:return __x6__
  |      } else 10:{}
  |      16:return resultCapability.Promise
  |    } else 10:{}
  |    17:app __x7__ = (IteratorValue next)
  |    17:let nextValue = __x7__
  |    18:app __x8__ = (IsAbruptCompletion nextValue)
  |    18:if __x8__ iteratorRecord.Done = true else 10:{}
  |    19:[? nextValue]
  |    20:append undefined -> errors
  |    21:app __x9__ = (Call promiseResolve constructor (new [nextValue]))
  |    21:let nextPromise = [? __x9__]
  |    22:??? "Let id:{stepsRejected} be the algorithm steps defined in link:{unhandled: sec-promise.any-reject-element-functions} ."
  |    23:??? "Let id:{lengthRejected} be the number of non - optional parameters of the function definition in link:{unhandled: sec-promise.any-reject-element-functions} ."
  |    24:app __x10__ = (CreateBuiltinFunction stepsRejected lengthRejected "" (new ["AlreadyCalled", "Index", "Errors", "Capability", "RemainingElements"]))
  |    24:let onRejected = [! __x10__]
  |    25:onRejected.AlreadyCalled = false
  |    26:onRejected.Index = index
  |    27:onRejected.Errors = errors
  |    28:onRejected.Capability = resultCapability
  |    29:onRejected.RemainingElements = remainingElementsCount
  |    30:remainingElementsCount.Value = (+ remainingElementsCount.Value 1i)
  |    31:app __x11__ = (Invoke nextPromise "then" (new [resultCapability.Resolve, onRejected]))
  |    31:[? __x11__]
  |    32:index = (+ index 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: ! IsConstructor(_constructor_) is *true*.""",
    """            1. Assert: ! IsCallable(_promiseResolve_) is *true*.""",
    """            1. Let _errors_ be a new empty List.""",
    """            1. Let _remainingElementsCount_ be the Record { [[Value]]: 1 }.""",
    """            1. Let _index_ be 0.""",
    """            1. Repeat,""",
    """              1. Let _next_ be IteratorStep(_iteratorRecord_).""",
    """              1. If _next_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_next_).""",
    """              1. If _next_ is *false*, then""",
    """                1. Set _iteratorRecord_.[[Done]] to *true*.""",
    """                1. Set _remainingElementsCount_.[[Value]] to _remainingElementsCount_.[[Value]] - 1.""",
    """                1. If _remainingElementsCount_.[[Value]] is 0, then""",
    """                  1. Let _error_ be a newly created `AggregateError` object.""",
    """                  1. Perform ! DefinePropertyOrThrow(_error_, *"errors"*, PropertyDescriptor { [[Configurable]]: *true*, [[Enumerable]]: *false*, [[Writable]]: *true*, [[Value]]: ! CreateArrayFromList(_errors_) }).""",
    """                  1. Return ThrowCompletion(_error_).""",
    """                1. Return _resultCapability_.[[Promise]].""",
    """              1. Let _nextValue_ be IteratorValue(_next_).""",
    """              1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_nextValue_).""",
    """              1. Append *undefined* to _errors_.""",
    """              1. Let _nextPromise_ be ? Call(_promiseResolve_, _constructor_, « _nextValue_ »).""",
    """              1. Let _stepsRejected_ be the algorithm steps defined in <emu-xref href="#sec-promise.any-reject-element-functions" title></emu-xref>.""",
    """              1. Let _lengthRejected_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-promise.any-reject-element-functions" title></emu-xref>.""",
    """              1. Let _onRejected_ be ! CreateBuiltinFunction(_stepsRejected_, _lengthRejected_, *""*, « [[AlreadyCalled]], [[Index]], [[Errors]], [[Capability]], [[RemainingElements]] »).""",
    """              1. Set _onRejected_.[[AlreadyCalled]] to *false*.""",
    """              1. Set _onRejected_.[[Index]] to _index_.""",
    """              1. Set _onRejected_.[[Errors]] to _errors_.""",
    """              1. Set _onRejected_.[[Capability]] to _resultCapability_.""",
    """              1. Set _onRejected_.[[RemainingElements]] to _remainingElementsCount_.""",
    """              1. Set _remainingElementsCount_.[[Value]] to _remainingElementsCount_.[[Value]] + 1.""",
    """              1. Perform ? Invoke(_nextPromise_, *"then"*, « _resultCapability_.[[Resolve]], _onRejected_ »).""",
    """              1. Set _index_ to _index_ + 1.""",
  )
}
