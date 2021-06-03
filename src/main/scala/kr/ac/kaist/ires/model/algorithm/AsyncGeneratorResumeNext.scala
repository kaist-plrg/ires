package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorResumeNext` extends Algo {
  val head = NormalHead("AsyncGeneratorResumeNext", List(Param("generator", Normal)))
  val ids = List(
    "sec-asyncgeneratorresumenext",
    "sec-asyncgenerator-abstract-operations",
    "sec-asyncgenerator-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  1:let state = generator.AsyncGeneratorState
  |  2:assert (! (= state CONST_executing))
  |  3:if (= state CONST_awaitingDASHreturn) return undefined else 40:{}
  |  4:let queue = generator.AsyncGeneratorQueue
  |  5:if (= queue.length 0i) return undefined else 40:{}
  |  6:let next = queue[0i]
  |  8:let completion = next.Completion
  |  31:app __x0__ = (IsAbruptCompletion completion)
  |  31:if __x0__ {
  |    10:if (= state CONST_suspendedStart) {
  |      11:generator.AsyncGeneratorState = CONST_completed
  |      12:state = CONST_completed
  |    } else 40:{}
  |    13:if (= state CONST_completed) if (= completion.Type CONST_return) {
  |      15:generator.AsyncGeneratorState = CONST_awaitingDASHreturn
  |      16:app __x1__ = (PromiseResolve INTRINSIC_Promise completion.Value)
  |      16:let promise = [? __x1__]
  |      17:let stepsFulfilled = AsyncGeneratorResumeNextReturnProcessorFulfilledFunctions
  |      18:??? "Let id:{lengthFulfilled} be the number of non - optional parameters of the function definition in AsyncGeneratorResumeNextReturnProcessorFulfilledFunctions ."
  |      19:app __x2__ = (CreateBuiltinFunction stepsFulfilled lengthFulfilled "" (new ["Generator"]))
  |      19:let onFulfilled = [! __x2__]
  |      20:onFulfilled.Generator = generator
  |      21:let stepsRejected = AsyncGeneratorResumeNextReturnProcessorRejectedFunctions
  |      22:??? "Let id:{lengthRejected} be the number of non - optional parameters of the function definition in AsyncGeneratorResumeNextReturnProcessorRejectedFunctions ."
  |      23:app __x3__ = (CreateBuiltinFunction stepsRejected lengthRejected "" (new ["Generator"]))
  |      23:let onRejected = [! __x3__]
  |      24:onRejected.Generator = generator
  |      25:app __x4__ = (PerformPromiseThen promise onFulfilled onRejected)
  |      25:[! __x4__]
  |      26:return undefined
  |    } else {
  |      28:assert (= completion.Type CONST_throw)
  |      29:app __x5__ = (AsyncGeneratorReject generator completion.Value)
  |      29:[! __x5__]
  |      30:return undefined
  |    } else 40:{}
  |  } else if (= state CONST_completed) {
  |    app __x6__ = (AsyncGeneratorResolve generator undefined true)
  |    return [! __x6__]
  |  } else 40:{}
  |  32:assert (|| (= state CONST_suspendedStart) (= state CONST_suspendedYield))
  |  33:let genContext = generator.AsyncGeneratorContext
  |  34:let callerContext = CONTEXT
  |  35:CONTEXT = null
  |  36:generator.AsyncGeneratorState = CONST_executing
  |  37:append genContext -> EXECUTION_STACK
  |  37:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  38:??? "Resume the suspended evaluation of id:{genContext} using id:{completion} as the result of the operation that suspended it . Let id:{result} be the completion record returned by the resumed computation ."
  |  41:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: _generator_ is an AsyncGenerator instance.""",
    """          1. Let _state_ be _generator_.[[AsyncGeneratorState]].""",
    """          1. Assert: _state_ is not ~executing~.""",
    """          1. If _state_ is ~awaiting-return~, return *undefined*.""",
    """          1. Let _queue_ be _generator_.[[AsyncGeneratorQueue]].""",
    """          1. If _queue_ is an empty List, return *undefined*.""",
    """          1. Let _next_ be the value of the first element of _queue_.""",
    """          1. Assert: _next_ is an AsyncGeneratorRequest record.""",
    """          1. Let _completion_ be _next_.[[Completion]].""",
    """          1. If _completion_ is an abrupt completion, then""",
    """            1. If _state_ is ~suspendedStart~, then""",
    """              1. Set _generator_.[[AsyncGeneratorState]] to ~completed~.""",
    """              1. Set _state_ to ~completed~.""",
    """            1. If _state_ is ~completed~, then""",
    """              1. If _completion_.[[Type]] is ~return~, then""",
    """                1. Set _generator_.[[AsyncGeneratorState]] to ~awaiting-return~.""",
    """                1. Let _promise_ be ? PromiseResolve(%Promise%, _completion_.[[Value]]).""",
    """                1. Let _stepsFulfilled_ be the algorithm steps defined in <emu-xref href="#async-generator-resume-next-return-processor-fulfilled" title></emu-xref>.""",
    """                1. Let _lengthFulfilled_ be the number of non-optional parameters of the function definition in <emu-xref href="#async-generator-resume-next-return-processor-fulfilled" title></emu-xref>.""",
    """                1. Let _onFulfilled_ be ! CreateBuiltinFunction(_stepsFulfilled_, _lengthFulfilled_, *""*, « [[Generator]] »).""",
    """                1. Set _onFulfilled_.[[Generator]] to _generator_.""",
    """                1. Let _stepsRejected_ be the algorithm steps defined in <emu-xref href="#async-generator-resume-next-return-processor-rejected" title></emu-xref>.""",
    """                1. Let _lengthRejected_ be the number of non-optional parameters of the function definition in <emu-xref href="#async-generator-resume-next-return-processor-rejected" title></emu-xref>.""",
    """                1. Let _onRejected_ be ! CreateBuiltinFunction(_stepsRejected_, _lengthRejected_, *""*, « [[Generator]] »).""",
    """                1. Set _onRejected_.[[Generator]] to _generator_.""",
    """                1. Perform ! PerformPromiseThen(_promise_, _onFulfilled_, _onRejected_).""",
    """                1. Return *undefined*.""",
    """              1. Else,""",
    """                1. Assert: _completion_.[[Type]] is ~throw~.""",
    """                1. Perform ! AsyncGeneratorReject(_generator_, _completion_.[[Value]]).""",
    """                1. Return *undefined*.""",
    """          1. Else if _state_ is ~completed~, return ! AsyncGeneratorResolve(_generator_, *undefined*, *true*).""",
    """          1. Assert: _state_ is either ~suspendedStart~ or ~suspendedYield~.""",
    """          1. Let _genContext_ be _generator_.[[AsyncGeneratorContext]].""",
    """          1. Let _callerContext_ be the running execution context.""",
    """          1. Suspend _callerContext_.""",
    """          1. Set _generator_.[[AsyncGeneratorState]] to ~executing~.""",
    """          1. Push _genContext_ onto the execution context stack; _genContext_ is now the running execution context.""",
    """          1. Resume the suspended evaluation of _genContext_ using _completion_ as the result of the operation that suspended it. Let _result_ be the completion record returned by the resumed computation.""",
    """          1. Assert: _result_ is never an abrupt completion.""",
    """          1. Assert: When we return here, _genContext_ has already been removed from the execution context stack and _callerContext_ is the currently running execution context.""",
    """          1. Return *undefined*.""",
  )
}
