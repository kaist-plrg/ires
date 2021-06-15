package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExecuteModule` extends Algo {
  val head = NormalHead("ExecuteModule", List())
  val ids = List(
    "sec-source-text-module-record-execute-module",
    "sec-source-text-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:CONTEXT = null
  |  1:let moduleContext = module.Context
  |  2:append moduleContext -> EXECUTION_STACK
  |  2:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  3:access __x0__ = (module.ECMAScriptCode "Evaluation")
  |  3:let result = __x0__
  |  4:CONTEXT = null
  |  4:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] moduleContext) (pop EXECUTION_STACK (- EXECUTION_STACK.length 1i)) else 11:{}
  |  5:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  6:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Suspend the currently running execution context.""",
    """            1. Let _moduleContext_ be _module_.[[Context]].""",
    """            1. Push _moduleContext_ onto the execution context stack; _moduleContext_ is now the running execution context.""",
    """            1. Let _result_ be the result of evaluating _module_.[[ECMAScriptCode]].""",
    """            1. Suspend _moduleContext_ and remove it from the execution context stack.""",
    """            1. Resume the context that is now on the top of the execution context stack as the running execution context.""",
    """            1. Return Completion(_result_).""",
  )
}
