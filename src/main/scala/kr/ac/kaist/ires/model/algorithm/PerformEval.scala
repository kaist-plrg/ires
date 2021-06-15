package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PerformEval` extends Algo {
  val head = NormalHead("PerformEval", List(Param("x", Normal), Param("callerRealm", Normal), Param("strictCaller", Normal), Param("direct", Normal)))
  val ids = List(
    "sec-performeval",
    "sec-eval-x",
    "sec-function-properties-of-the-global-object",
    "sec-global-object",
  )
  val rawBody = parseInst("""{
  |  1:if (! (= (typeof x) String)) return x else 25:{}
  |  2:let evalRealm = REALM
  |  3:app __x0__ = (HostEnsureCanCompileStrings callerRealm evalRealm)
  |  3:[? __x0__]
  |  4:let inFunction = false
  |  5:let inMethod = false
  |  6:let inDerivedConstructor = false
  |  7:if (= direct true) {
  |    8:app __x1__ = (GetThisEnvironment)
  |    8:let thisEnvRec = [! __x1__]
  |    9:if (is-instance-of thisEnvRec FunctionEnvironmentRecord) {
  |      10:let F = thisEnvRec.FunctionObject
  |      11:inFunction = true
  |      12:app __x2__ = (thisEnvRec.HasSuperBinding thisEnvRec)
  |      12:inMethod = __x2__
  |      13:if (= F.ConstructorKind CONST_derived) inDerivedConstructor = true else 25:{}
  |    } else 25:{}
  |  } else 25:{}
  |  14:let parameterGoal = AST_FormalParameters
  |  14:let script = AST_Script
  |  14:let body = AST_ScriptBody
  |  23:if (= strictCaller true) let strictEval = true else {
  |    access __x3__ = (script "IsStrict")
  |    let strictEval = __x3__
  |  }
  |  24:let runningContext = CONTEXT
  |  29:if (= direct true) {
  |    27:app __x4__ = (NewDeclarativeEnvironment runningContext.LexicalEnvironment)
  |    27:let lexEnv = __x4__
  |    28:let varEnv = runningContext.VariableEnvironment
  |  } else {
  |    30:app __x5__ = (NewDeclarativeEnvironment evalRealm.GlobalEnv)
  |    30:let lexEnv = __x5__
  |    31:let varEnv = evalRealm.GlobalEnv
  |  }
  |  32:if (= strictEval true) varEnv = lexEnv else 25:{}
  |  33:if (= runningContext null) CONTEXT = null else 25:{}
  |  34:let evalContext = (new ExecutionContext("SubMap" -> (new SubMap())))
  |  35:evalContext.Function = null
  |  36:evalContext.Realm = evalRealm
  |  37:evalContext.ScriptOrModule = runningContext.ScriptOrModule
  |  38:evalContext.VariableEnvironment = varEnv
  |  39:evalContext.LexicalEnvironment = lexEnv
  |  40:append evalContext -> EXECUTION_STACK
  |  40:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  41:app __x6__ = (EvalDeclarationInstantiation body varEnv lexEnv strictEval)
  |  41:let result = __x6__
  |  42:if (= result.Type CONST_normal) {
  |    43:access __x7__ = (body "Evaluation")
  |    43:result = __x7__
  |  } else 25:{}
  |  44:if (&& (= result.Type CONST_normal) (= result.Value CONST_empty)) {
  |    45:app __x8__ = (NormalCompletion undefined)
  |    45:result = __x8__
  |  } else 25:{}
  |  46:CONTEXT = null
  |  46:if (= EXECUTION_STACK[(- EXECUTION_STACK.length 1i)] evalContext) (pop EXECUTION_STACK (- EXECUTION_STACK.length 1i)) else 25:{}
  |  47:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  48:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Assert: If _direct_ is *false*, then _strictCaller_ is also *false*.""",
    """          1. If Type(_x_) is not String, return _x_.""",
    """          1. Let _evalRealm_ be the current Realm Record.""",
    """          1. Perform ? HostEnsureCanCompileStrings(_callerRealm_, _evalRealm_).""",
    """          1. Let _inFunction_ be *false*.""",
    """          1. Let _inMethod_ be *false*.""",
    """          1. Let _inDerivedConstructor_ be *false*.""",
    """          1. If _direct_ is *true*, then""",
    """            1. Let _thisEnvRec_ be ! GetThisEnvironment().""",
    """            1. If _thisEnvRec_ is a function Environment Record, then""",
    """              1. Let _F_ be _thisEnvRec_.[[FunctionObject]].""",
    """              1. Set _inFunction_ to *true*.""",
    """              1. Set _inMethod_ to _thisEnvRec_.HasSuperBinding().""",
    """              1. If _F_.[[ConstructorKind]] is ~derived~, set _inDerivedConstructor_ to *true*.""",
    """          1. Perform the following substeps in an implementation-defined order, possibly interleaving parsing and error detection:""",
    """            1. Let _script_ be ParseText(! StringToCodePoints(_x_), |Script|).""",
    """            1. If _script_ is a List of errors, throw a *SyntaxError* exception.""",
    """            1. If _script_ Contains |ScriptBody| is *false*, return *undefined*.""",
    """            1. Let _body_ be the |ScriptBody| of _script_.""",
    """            1. If _inFunction_ is *false*, and _body_ Contains |NewTarget|, throw a *SyntaxError* exception.""",
    """            1. If _inMethod_ is *false*, and _body_ Contains |SuperProperty|, throw a *SyntaxError* exception.""",
    """            1. If _inDerivedConstructor_ is *false*, and _body_ Contains |SuperCall|, throw a *SyntaxError* exception.""",
    """          1. If _strictCaller_ is *true*, let _strictEval_ be *true*.""",
    """          1. Else, let _strictEval_ be IsStrict of _script_.""",
    """          1. Let _runningContext_ be the running execution context.""",
    """          1. NOTE: If _direct_ is *true*, _runningContext_ will be the execution context that performed the direct eval. If _direct_ is *false*, _runningContext_ will be the execution context for the invocation of the `eval` function.""",
    """          1. If _direct_ is *true*, then""",
    """            1. Let _lexEnv_ be NewDeclarativeEnvironment(_runningContext_'s LexicalEnvironment).""",
    """            1. Let _varEnv_ be _runningContext_'s VariableEnvironment.""",
    """          1. Else,""",
    """            1. Let _lexEnv_ be NewDeclarativeEnvironment(_evalRealm_.[[GlobalEnv]]).""",
    """            1. Let _varEnv_ be _evalRealm_.[[GlobalEnv]].""",
    """          1. If _strictEval_ is *true*, set _varEnv_ to _lexEnv_.""",
    """          1. If _runningContext_ is not already suspended, suspend _runningContext_.""",
    """          1. Let _evalContext_ be a new ECMAScript code execution context.""",
    """          1. Set _evalContext_'s Function to *null*.""",
    """          1. Set _evalContext_'s Realm to _evalRealm_.""",
    """          1. Set _evalContext_'s ScriptOrModule to _runningContext_'s ScriptOrModule.""",
    """          1. Set _evalContext_'s VariableEnvironment to _varEnv_.""",
    """          1. Set _evalContext_'s LexicalEnvironment to _lexEnv_.""",
    """          1. Push _evalContext_ onto the execution context stack; _evalContext_ is now the running execution context.""",
    """          1. Let _result_ be EvalDeclarationInstantiation(_body_, _varEnv_, _lexEnv_, _strictEval_).""",
    """          1. If _result_.[[Type]] is ~normal~, then""",
    """            1. Set _result_ to the result of evaluating _body_.""",
    """          1. If _result_.[[Type]] is ~normal~ and _result_.[[Value]] is ~empty~, then""",
    """            1. Set _result_ to NormalCompletion(*undefined*).""",
    """          1. Suspend _evalContext_ and remove it from the execution context stack.""",
    """          1. Resume the context that is now on the top of the execution context stack as the running execution context.""",
    """          1. Return Completion(_result_).""",
  )
}
