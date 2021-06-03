package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CallExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("CallExpression", 0, 0, Rhs(List(NonTerminal("CoverCallExpressionAndAsyncArrowHead", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-function-calls-runtime-semantics-evaluation",
    "sec-function-calls",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverCallExpressionAndAsyncArrowHead "CoveredCallExpression")
  |  0:let expr = __x0__
  |  1:access __x1__ = (expr "MemberExpression")
  |  1:let memberExpr = __x1__
  |  2:access __x2__ = (expr "Arguments")
  |  2:let arguments = __x2__
  |  3:access __x3__ = (memberExpr "Evaluation")
  |  3:let ref = __x3__
  |  4:app __x4__ = (GetValue ref)
  |  4:let func = [? __x4__]
  |  5:let __x5__ = true
  |  5:__x5__ = (is-instance-of ref ReferenceRecord)
  |  5:if __x5__ {
  |    app __x6__ = (IsPropertyReference ref)
  |    __x5__ = (= __x6__ false)
  |    if __x5__ __x5__ = (= ref.ReferencedName "eval") else 8:{}
  |  } else 8:{}
  |  5:if __x5__ {
  |    6:app __x7__ = (SameValue func INTRINSIC_eval)
  |    6:if (= __x7__ true) {
  |      7:access __x8__ = (arguments "ArgumentListEvaluation")
  |      7:let argList = [? __x8__]
  |      8:if (= argList.length 0i) return undefined else 8:{}
  |      9:let evalArg = argList[0i]
  |      10:if true let strictCaller = true else let strictCaller = false
  |      11:let evalRealm = REALM
  |      12:app __x9__ = (PerformEval evalArg evalRealm strictCaller true)
  |      12:return [? __x9__]
  |    } else 8:{}
  |  } else 8:{}
  |  13:let thisCall = this
  |  14:app __x10__ = (IsInTailPosition thisCall)
  |  14:let tailCall = __x10__
  |  15:app __x11__ = (EvaluateCall func ref arguments tailCall)
  |  15:return [? __x11__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _expr_ be CoveredCallExpression of |CoverCallExpressionAndAsyncArrowHead|.""",
    """          1. Let _memberExpr_ be the |MemberExpression| of _expr_.""",
    """          1. Let _arguments_ be the |Arguments| of _expr_.""",
    """          1. Let _ref_ be the result of evaluating _memberExpr_.""",
    """          1. Let _func_ be ? GetValue(_ref_).""",
    """          1. If _ref_ is a Reference Record, IsPropertyReference(_ref_) is *false*, and _ref_.[[ReferencedName]] is *"eval"*, then""",
    """            1. If SameValue(_func_, %eval%) is *true*, then""",
    """              1. Let _argList_ be ? ArgumentListEvaluation of _arguments_.""",
    """              1. If _argList_ has no elements, return *undefined*.""",
    """              1. Let _evalArg_ be the first element of _argList_.""",
    """              1. If the source code matching this |CallExpression| is strict mode code, let _strictCaller_ be *true*. Otherwise let _strictCaller_ be *false*.""",
    """              1. Let _evalRealm_ be the current Realm Record.""",
    """              1. [id="step-callexpression-evaluation-direct-eval"] Return ? PerformEval(_evalArg_, _evalRealm_, _strictCaller_, *true*).""",
    """          1. Let _thisCall_ be this |CallExpression|.""",
    """          1. Let _tailCall_ be IsInTailPosition(_thisCall_).""",
    """          1. Return ? EvaluateCall(_func_, _ref_, _arguments_, _tailCall_).""",
  )
}
