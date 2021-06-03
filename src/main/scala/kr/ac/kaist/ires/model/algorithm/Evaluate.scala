package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Evaluate` extends Algo {
  val head = NormalHead("Evaluate", List())
  val ids = List(
    "sec-moduleevaluation",
    "sec-cyclic-module-records",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  1:assert (|| (= module.Status CONST_linked) (= module.Status CONST_evaluated))
  |  2:let stack = (new [])
  |  3:app __x0__ = (InnerModuleEvaluation module stack 0i)
  |  3:let result = __x0__
  |  4:app __x1__ = (IsAbruptCompletion result)
  |  4:if __x1__ {
  |    5:let __x2__ = stack
  |    5:let __x3__ = 0i
  |    5:while (< __x3__ __x2__.length) {
  |      let m = __x2__[__x3__]
  |      6:assert (= m.Status CONST_evaluating)
  |      7:m.Status = CONST_evaluated
  |      8:m.EvaluationError = result
  |      __x3__ = (+ __x3__ 1i)
  |    }
  |    9:assert (&& (= module.Status CONST_evaluated) (= module.EvaluationError result))
  |    10:return result
  |  } else 0:{}
  |  11:assert (&& (= module.Status CONST_evaluated) (= module.EvaluationError undefined))
  |  12:assert (= stack.length 0i)
  |  13:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Assert: This call to Evaluate is not happening at the same time as another call to Evaluate within the surrounding agent.""",
    """            1. Assert: _module_.[[Status]] is ~linked~ or ~evaluated~.""",
    """            1. Let _stack_ be a new empty List.""",
    """            1. Let _result_ be InnerModuleEvaluation(_module_, _stack_, 0).""",
    """            1. If _result_ is an abrupt completion, then""",
    """              1. For each Cyclic Module Record _m_ of _stack_, do""",
    """                1. Assert: _m_.[[Status]] is ~evaluating~.""",
    """                1. Set _m_.[[Status]] to ~evaluated~.""",
    """                1. Set _m_.[[EvaluationError]] to _result_.""",
    """              1. Assert: _module_.[[Status]] is ~evaluated~ and _module_.[[EvaluationError]] is _result_.""",
    """              1. Return _result_.""",
    """            1. Assert: _module_.[[Status]] is ~evaluated~ and _module_.[[EvaluationError]] is *undefined*.""",
    """            1. Assert: _stack_ is empty.""",
    """            1. Return *undefined*.""",
  )
}
