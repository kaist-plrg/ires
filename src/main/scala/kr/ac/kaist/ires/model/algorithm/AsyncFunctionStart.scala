package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncFunctionStart` extends Algo {
  val head = NormalHead("AsyncFunctionStart", List(Param("promiseCapability", Normal), Param("asyncFunctionBody", Normal)))
  val ids = List(
    "sec-async-functions-abstract-operations-async-function-start",
    "sec-async-functions-abstract-operations",
    "sec-async-function-objects",
    "sec-control-abstraction-objects",
  )
  val rawBody = parseInst("""{
  |  0:let runningContext = CONTEXT
  |  1:let asyncContext = (copy-obj runningContext)
  |  3:??? "Set the code evaluation state of id:{asyncContext} such that when evaluation is resumed for that execution context the following steps will be performed : in:{} out:{}"
  |  15:append asyncContext -> EXECUTION_STACK
  |  15:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  16:??? "Resume the suspended evaluation of id:{asyncContext} . Let id:{result} be the value returned by the resumed computation ."
  |  19:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _runningContext_ be the running execution context.""",
    """          1. Let _asyncContext_ be a copy of _runningContext_.""",
    """          1. NOTE: Copying the execution state is required for the step below to resume its execution. It is ill-defined to resume a currently executing context.""",
    """          1. Set the code evaluation state of _asyncContext_ such that when evaluation is resumed for that execution context the following steps will be performed:""",
    """            1. Let _result_ be the result of evaluating _asyncFunctionBody_.""",
    """            1. Assert: If we return here, the async function either threw an exception or performed an implicit or explicit return; all awaiting is done.""",
    """            1. Remove _asyncContext_ from the execution context stack and restore the execution context that is at the top of the execution context stack as the running execution context.""",
    """            1. If _result_.[[Type]] is ~normal~, then""",
    """              1. Perform ! Call(_promiseCapability_.[[Resolve]], *undefined*, « *undefined* »).""",
    """            1. Else if _result_.[[Type]] is ~return~, then""",
    """              1. Perform ! Call(_promiseCapability_.[[Resolve]], *undefined*, « _result_.[[Value]] »).""",
    """            1. Else,""",
    """              1. Assert: _result_.[[Type]] is ~throw~.""",
    """              1. Perform ! Call(_promiseCapability_.[[Reject]], *undefined*, « _result_.[[Value]] »).""",
    """            1. [id="step-asyncfunctionstart-return-undefined"] Return.""",
    """          1. Push _asyncContext_ onto the execution context stack; _asyncContext_ is now the running execution context.""",
    """          1. Resume the suspended evaluation of _asyncContext_. Let _result_ be the value returned by the resumed computation.""",
    """          1. Assert: When we return here, _asyncContext_ has already been removed from the execution context stack and _runningContext_ is the currently running execution context.""",
    """          1. Assert: _result_ is a normal completion with a value of *undefined*. The possible sources of completion values are Await or, if the async function doesn't await anything, step <emu-xref href="#step-asyncfunctionstart-return-undefined"></emu-xref> above.""",
    """          1. Return.""",
  )
}
