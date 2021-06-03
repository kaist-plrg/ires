package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GetActiveScriptOrModule` extends Algo {
  val head = NormalHead("GetActiveScriptOrModule", List())
  val ids = List(
    "sec-getactivescriptormodule",
    "sec-execution-contexts",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:if (= EXECUTION_STACK.length 0i) return null else 2:{}
  |  1:let ec = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  2:return ec.ScriptOrModule
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the execution context stack is empty, return *null*.""",
    """        1. Let _ec_ be the topmost execution context on the execution context stack whose ScriptOrModule component is not *null*.""",
    """        1. If no such execution context exists, return *null*. Otherwise, return _ec_'s ScriptOrModule.""",
  )
}
