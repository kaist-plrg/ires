package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AwaitRejectedFunctions` extends Algo {
  val head = NormalHead("AwaitRejectedFunctions", List())
  val ids = List(
    "await-rejected",
    "await",
    "sec-completion-record-specification-type",
    "sec-ecmascript-specification-types",
    "sec-ecmascript-data-types-and-values",
  )
  val rawBody = parseInst("""{
  |  0:let F = CONTEXT.Function
  |  1:let asyncContext = F.AsyncContext
  |  2:let prevContext = CONTEXT
  |  3:CONTEXT = null
  |  4:append asyncContext -> EXECUTION_STACK
  |  4:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  5:??? "Resume the suspended evaluation of asyncContext using (ThrowCompletion~reason) as the result of the operation that suspended it"
  |  7:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Let _asyncContext_ be _F_.[[AsyncContext]].""",
    """            1. Let _prevContext_ be the running execution context.""",
    """            1. Suspend _prevContext_.""",
    """            1. Push _asyncContext_ onto the execution context stack; _asyncContext_ is now the running execution context.""",
    """            1. Resume the suspended evaluation of _asyncContext_ using ThrowCompletion(_reason_) as the result of the operation that suspended it.""",
    """            1. Assert: When we reach this step, _asyncContext_ has already been removed from the execution context stack and _prevContext_ is the currently running execution context.""",
    """            1. Return *undefined*.""",
  )
}
