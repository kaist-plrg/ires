package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Await` extends Algo {
  val head = NormalHead("Await", List(Param("value", Normal)))
  val ids = List(
    "await",
    "sec-completion-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:let asyncContext = CONTEXT
  |  1:app __x0__ = (PromiseResolve INTRINSIC_Promise value)
  |  1:let promise = [? __x0__]
  |  2:??? "Let id:{stepsFulfilled} be the algorithm steps defined in link:{await-fulfilled} ."
  |  3:??? "Let id:{lengthFulfilled} be the number of non - optional parameters of the function definition in link:{await-fulfilled} ."
  |  4:app __x1__ = (CreateBuiltinFunction stepsFulfilled lengthFulfilled "" (new ["AsyncContext"]))
  |  4:let onFulfilled = [! __x1__]
  |  5:onFulfilled.AsyncContext = asyncContext
  |  6:??? "Let id:{stepsRejected} be the algorithm steps defined in link:{await-rejected} ."
  |  7:??? "Let id:{lengthRejected} be the number of non - optional parameters of the function definition in link:{await-rejected} ."
  |  8:app __x2__ = (CreateBuiltinFunction stepsRejected lengthRejected "" (new ["AsyncContext"]))
  |  8:let onRejected = [! __x2__]
  |  9:onRejected.AsyncContext = asyncContext
  |  10:app __x3__ = (PerformPromiseThen promise onFulfilled onRejected)
  |  10:[! __x3__]
  |  11:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] asyncContext) {
  |    let __x4__ = (- EXECUTION_STACK.length 1i)
  |    (pop EXECUTION_STACK __x4__)
  |  } else {}
  |  11:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  12:??? "Set the code evaluation state of id:{asyncContext} such that when evaluation is resumed with a Completion id:{completion} , the following steps of the algorithm that invoked Await will be performed , with id:{completion} available ."
  |  13:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _asyncContext_ be the running execution context.""",
    """          1. Let _promise_ be ? PromiseResolve(%Promise%, _value_).""",
    """          1. Let _stepsFulfilled_ be the algorithm steps defined in <emu-xref href="#await-fulfilled" title></emu-xref>.""",
    """          1. Let _lengthFulfilled_ be the number of non-optional parameters of the function definition in <emu-xref href="#await-fulfilled" title></emu-xref>.""",
    """          1. Let _onFulfilled_ be ! CreateBuiltinFunction(_stepsFulfilled_, _lengthFulfilled_, *""*, « [[AsyncContext]] »).""",
    """          1. Set _onFulfilled_.[[AsyncContext]] to _asyncContext_.""",
    """          1. Let _stepsRejected_ be the algorithm steps defined in <emu-xref href="#await-rejected" title></emu-xref>.""",
    """          1. Let _lengthRejected_ be the number of non-optional parameters of the function definition in <emu-xref href="#await-rejected" title></emu-xref>.""",
    """          1. Let _onRejected_ be ! CreateBuiltinFunction(_stepsRejected_, _lengthRejected_, *""*, « [[AsyncContext]] »).""",
    """          1. Set _onRejected_.[[AsyncContext]] to _asyncContext_.""",
    """          1. Perform ! PerformPromiseThen(_promise_, _onFulfilled_, _onRejected_).""",
    """          1. Remove _asyncContext_ from the execution context stack and restore the execution context that is at the top of the execution context stack as the running execution context.""",
    """          1. Set the code evaluation state of _asyncContext_ such that when evaluation is resumed with a Completion _completion_, the following steps of the algorithm that invoked Await will be performed, with _completion_ available.""",
    """          1. Return.""",
    """          1. NOTE: This returns to the evaluation of the operation that had most previously resumed evaluation of _asyncContext_.""",
  )
}
