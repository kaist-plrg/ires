package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CallExpression[3,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("CallExpression", 3, 0, Rhs(List(NonTerminal("CallExpression", List(""), false), NonTerminal("Arguments", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-function-calls-runtime-semantics-evaluation",
    "sec-function-calls",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CallExpression "Evaluation")
  |  0:let ref = __x0__
  |  1:app __x1__ = (GetValue ref)
  |  1:let func = [? __x1__]
  |  2:let thisCall = this
  |  3:app __x2__ = (IsInTailPosition thisCall)
  |  3:let tailCall = __x2__
  |  4:app __x3__ = (EvaluateCall func ref Arguments tailCall)
  |  4:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _ref_ be the result of evaluating |CallExpression|.""",
    """          1. Let _func_ be ? GetValue(_ref_).""",
    """          1. Let _thisCall_ be this |CallExpression|.""",
    """          1. Let _tailCall_ be IsInTailPosition(_thisCall_).""",
    """          1. Return ? EvaluateCall(_func_, _ref_, |Arguments|, _tailCall_).""",
  )
}
