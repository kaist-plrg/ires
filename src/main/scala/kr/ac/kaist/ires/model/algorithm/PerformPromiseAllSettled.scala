package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PerformPromiseAllSettled` extends Algo {
  val head = NormalHead("PerformPromiseAllSettled", List(Param("iteratorRecord", Normal), Param("constructor", Normal), Param("resultCapability", Normal), Param("promiseResolve", Normal)))
  val ids = List(
    "sec-performpromiseallsettled",
    "sec-promise.allsettled",
    "sec-properties-of-the-promise-constructor",
    "sec-promise-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (IsConstructor constructor)
  |  0:assert (= [! __x0__] true)
  |  1:app __x1__ = (IsCallable promiseResolve)
  |  1:assert (= __x1__ true)
  |  2:let values = (new [])
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
  |        13:app __x4__ = (CreateArrayFromList values)
  |        13:let valuesArray = [! __x4__]
  |        14:app __x5__ = (Call resultCapability.Resolve undefined (new [valuesArray]))
  |        14:[? __x5__]
  |      } else 10:{}
  |      15:return resultCapability.Promise
  |    } else 10:{}
  |    16:app __x6__ = (IteratorValue next)
  |    16:let nextValue = __x6__
  |    17:app __x7__ = (IsAbruptCompletion nextValue)
  |    17:if __x7__ iteratorRecord.Done = true else 10:{}
  |    18:[? nextValue]
  |    19:append undefined -> values
  |    20:app __x8__ = (Call promiseResolve constructor (new [nextValue]))
  |    20:let nextPromise = [? __x8__]
  |    21:??? "Let id:{stepsFulfilled} be the algorithm steps defined in link:{unhandled: sec-promise.allsettled-resolve-element-functions} ."
  |    22:??? "Let id:{lengthFulfilled} be the number of non - optional parameters of the function definition in link:{unhandled: sec-promise.allsettled-resolve-element-functions} ."
  |    23:app __x9__ = (CreateBuiltinFunction stepsFulfilled lengthFulfilled "" (new ["AlreadyCalled", "Index", "Values", "Capability", "RemainingElements"]))
  |    23:let onFulfilled = [! __x9__]
  |    24:let alreadyCalled = (new Record("Value" -> false))
  |    25:onFulfilled.AlreadyCalled = alreadyCalled
  |    26:onFulfilled.Index = index
  |    27:onFulfilled.Values = values
  |    28:onFulfilled.Capability = resultCapability
  |    29:onFulfilled.RemainingElements = remainingElementsCount
  |    30:??? "Let id:{stepsRejected} be the algorithm steps defined in link:{unhandled: sec-promise.allsettled-reject-element-functions} ."
  |    31:??? "Let id:{lengthRejected} be the number of non - optional parameters of the function definition in link:{unhandled: sec-promise.allsettled-reject-element-functions} ."
  |    32:app __x10__ = (CreateBuiltinFunction stepsRejected lengthRejected "" (new ["AlreadyCalled", "Index", "Values", "Capability", "RemainingElements"]))
  |    32:let onRejected = [! __x10__]
  |    33:onRejected.AlreadyCalled = alreadyCalled
  |    34:onRejected.Index = index
  |    35:onRejected.Values = values
  |    36:onRejected.Capability = resultCapability
  |    37:onRejected.RemainingElements = remainingElementsCount
  |    38:remainingElementsCount.Value = (+ remainingElementsCount.Value 1i)
  |    39:app __x11__ = (Invoke nextPromise "then" (new [onFulfilled, onRejected]))
  |    39:[? __x11__]
  |    40:index = (+ index 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: ! IsConstructor(_constructor_) is *true*.""",
    """            1. Assert: IsCallable(_promiseResolve_) is *true*.""",
    """            1. Let _values_ be a new empty List.""",
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
    """                  1. Let _valuesArray_ be ! CreateArrayFromList(_values_).""",
    """                  1. Perform ? Call(_resultCapability_.[[Resolve]], *undefined*, « _valuesArray_ »).""",
    """                1. Return _resultCapability_.[[Promise]].""",
    """              1. Let _nextValue_ be IteratorValue(_next_).""",
    """              1. If _nextValue_ is an abrupt completion, set _iteratorRecord_.[[Done]] to *true*.""",
    """              1. ReturnIfAbrupt(_nextValue_).""",
    """              1. Append *undefined* to _values_.""",
    """              1. Let _nextPromise_ be ? Call(_promiseResolve_, _constructor_, « _nextValue_ »).""",
    """              1. Let _stepsFulfilled_ be the algorithm steps defined in <emu-xref href="#sec-promise.allsettled-resolve-element-functions" title></emu-xref>.""",
    """              1. Let _lengthFulfilled_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-promise.allsettled-resolve-element-functions" title></emu-xref>.""",
    """              1. Let _onFulfilled_ be ! CreateBuiltinFunction(_stepsFulfilled_, _lengthFulfilled_, *""*, « [[AlreadyCalled]], [[Index]], [[Values]], [[Capability]], [[RemainingElements]] »).""",
    """              1. Let _alreadyCalled_ be the Record { [[Value]]: *false* }.""",
    """              1. Set _onFulfilled_.[[AlreadyCalled]] to _alreadyCalled_.""",
    """              1. Set _onFulfilled_.[[Index]] to _index_.""",
    """              1. Set _onFulfilled_.[[Values]] to _values_.""",
    """              1. Set _onFulfilled_.[[Capability]] to _resultCapability_.""",
    """              1. Set _onFulfilled_.[[RemainingElements]] to _remainingElementsCount_.""",
    """              1. Let _stepsRejected_ be the algorithm steps defined in <emu-xref href="#sec-promise.allsettled-reject-element-functions" title></emu-xref>.""",
    """              1. Let _lengthRejected_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-promise.allsettled-reject-element-functions" title></emu-xref>.""",
    """              1. Let _onRejected_ be ! CreateBuiltinFunction(_stepsRejected_, _lengthRejected_, *""*, « [[AlreadyCalled]], [[Index]], [[Values]], [[Capability]], [[RemainingElements]] »).""",
    """              1. Set _onRejected_.[[AlreadyCalled]] to _alreadyCalled_.""",
    """              1. Set _onRejected_.[[Index]] to _index_.""",
    """              1. Set _onRejected_.[[Values]] to _values_.""",
    """              1. Set _onRejected_.[[Capability]] to _resultCapability_.""",
    """              1. Set _onRejected_.[[RemainingElements]] to _remainingElementsCount_.""",
    """              1. Set _remainingElementsCount_.[[Value]] to _remainingElementsCount_.[[Value]] + 1.""",
    """              1. Perform ? Invoke(_nextPromise_, *"then"*, « _onFulfilled_, _onRejected_ »).""",
    """              1. Set _index_ to _index_ + 1.""",
  )
}
