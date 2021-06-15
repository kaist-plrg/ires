package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportCall[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ImportCall", 0, 0, Rhs(List(Terminal("import"), Terminal("("), NonTerminal("AssignmentExpression", List(""), false), Terminal(")")), None), "Evaluation", List())
  val ids = List(
    "sec-import-call-runtime-semantics-evaluation",
    "sec-import-calls",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetActiveScriptOrModule)
  |  0:let referencingScriptOrModule = [! __x0__]
  |  1:access __x1__ = (AssignmentExpression "Evaluation")
  |  1:let argRef = __x1__
  |  2:app __x2__ = (GetValue argRef)
  |  2:let specifier = [? __x2__]
  |  3:app __x3__ = (NewPromiseCapability INTRINSIC_Promise)
  |  3:let promiseCapability = [! __x3__]
  |  4:app __x4__ = (ToString specifier)
  |  4:let specifierString = __x4__
  |  5:if (is-completion specifierString) if (= specifierString.Type CONST_normal) specifierString = specifierString.Value else {
  |    app __x5__ = (Call promiseCapability.Reject undefined (new [specifierString.Value]))
  |    if (&& (is-completion __x5__) (! (= __x5__.Type CONST_normal))) return __x5__ else 3:{}
  |    return promiseCapability.Promise
  |  } else 3:{}
  |  5:specifierString
  |  6:app __x6__ = (HostImportModuleDynamically referencingScriptOrModule specifierString promiseCapability)
  |  6:[! __x6__]
  |  7:return promiseCapability.Promise
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _referencingScriptOrModule_ be ! GetActiveScriptOrModule().""",
    """          1. Let _argRef_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _specifier_ be ? GetValue(_argRef_).""",
    """          1. Let _promiseCapability_ be ! NewPromiseCapability(%Promise%).""",
    """          1. Let _specifierString_ be ToString(_specifier_).""",
    """          1. IfAbruptRejectPromise(_specifierString_, _promiseCapability_).""",
    """          1. Perform ! HostImportModuleDynamically(_referencingScriptOrModule_, _specifierString_, _promiseCapability_).""",
    """          1. Return _promiseCapability_.[[Promise]].""",
  )
}
