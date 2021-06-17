package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ScriptEvaluation` extends Algo {
  val head = NormalHead("ScriptEvaluation", List(Param("scriptRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-scriptevaluation",
    "sec-scripts",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:let globalEnv = scriptRecord.Realm.GlobalEnv
  |  1:let scriptContext = (new ExecutionContext())
  |  2:scriptContext.Function = null
  |  3:scriptContext.Realm = scriptRecord.Realm
  |  4:scriptContext.ScriptOrModule = scriptRecord
  |  5:scriptContext.VariableEnvironment = globalEnv
  |  6:scriptContext.LexicalEnvironment = globalEnv
  |  7:CONTEXT = null
  |  8:append scriptContext -> EXECUTION_STACK
  |  8:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  9:let scriptBody = scriptRecord.ECMAScriptCode
  |  10:app __x0__ = (GlobalDeclarationInstantiation scriptBody globalEnv)
  |  10:let result = __x0__
  |  11:if (= result.Type CONST_normal) {
  |    12:access __x1__ = (scriptBody "Evaluation")
  |    12:result = __x1__
  |  } else 2:{}
  |  13:if (&& (= result.Type CONST_normal) (= result.Value CONST_empty)) {
  |    14:app __x2__ = (NormalCompletion undefined)
  |    14:result = __x2__
  |  } else 2:{}
  |  15:CONTEXT = null
  |  15:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] scriptContext) (pop EXECUTION_STACK (- EXECUTION_STACK.length 1i)) else 2:{}
  |  16:assert (< 0i EXECUTION_STACK.length)
  |  17:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  18:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _globalEnv_ be _scriptRecord_.[[Realm]].[[GlobalEnv]].""",
    """        1. Let _scriptContext_ be a new ECMAScript code execution context.""",
    """        1. Set the Function of _scriptContext_ to *null*.""",
    """        1. Set the Realm of _scriptContext_ to _scriptRecord_.[[Realm]].""",
    """        1. Set the ScriptOrModule of _scriptContext_ to _scriptRecord_.""",
    """        1. Set the VariableEnvironment of _scriptContext_ to _globalEnv_.""",
    """        1. Set the LexicalEnvironment of _scriptContext_ to _globalEnv_.""",
    """        1. Suspend the currently running execution context.""",
    """        1. Push _scriptContext_ onto the execution context stack; _scriptContext_ is now the running execution context.""",
    """        1. Let _scriptBody_ be _scriptRecord_.[[ECMAScriptCode]].""",
    """        1. Let _result_ be GlobalDeclarationInstantiation(_scriptBody_, _globalEnv_).""",
    """        1. If _result_.[[Type]] is ~normal~, then""",
    """          1. Set _result_ to the result of evaluating _scriptBody_.""",
    """        1. If _result_.[[Type]] is ~normal~ and _result_.[[Value]] is ~empty~, then""",
    """          1. Set _result_ to NormalCompletion(*undefined*).""",
    """        1. Suspend _scriptContext_ and remove it from the execution context stack.""",
    """        1. Assert: The execution context stack is not empty.""",
    """        1. Resume the context that is now on the top of the execution context stack as the running execution context.""",
    """        1. Return Completion(_result_).""",
  )
}
