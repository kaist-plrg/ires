package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SuperCall[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("SuperCall", 0, 0, Rhs(List(Terminal("super"), NonTerminal("Arguments", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-super-keyword-runtime-semantics-evaluation",
    "sec-super-keyword",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (GetNewTarget)
  |  0:let newTarget = __x0__
  |  1:assert (= (typeof newTarget) Object)
  |  2:app __x1__ = (GetSuperConstructor)
  |  2:let func = [! __x1__]
  |  3:access __x2__ = (Arguments "ArgumentListEvaluation")
  |  3:let argList = [? __x2__]
  |  4:app __x3__ = (IsConstructor func)
  |  4:if (= __x3__ false) throw TypeError else 15:{}
  |  5:app __x4__ = (Construct func argList newTarget)
  |  5:let result = [? __x4__]
  |  6:app __x5__ = (GetThisEnvironment)
  |  6:let thisER = __x5__
  |  7:app __x6__ = (thisER.BindThisValue thisER result)
  |  7:return [? __x6__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _newTarget_ be GetNewTarget().""",
    """          1. Assert: Type(_newTarget_) is Object.""",
    """          1. Let _func_ be ! GetSuperConstructor().""",
    """          1. Let _argList_ be ? ArgumentListEvaluation of |Arguments|.""",
    """          1. If IsConstructor(_func_) is *false*, throw a *TypeError* exception.""",
    """          1. Let _result_ be ? Construct(_func_, _argList_, _newTarget_).""",
    """          1. Let _thisER_ be GetThisEnvironment().""",
    """          1. Return ? _thisER_.BindThisValue(_result_).""",
  )
}
