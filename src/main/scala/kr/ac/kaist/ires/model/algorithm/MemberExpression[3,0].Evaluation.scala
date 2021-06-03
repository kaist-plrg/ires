package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[3,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 3, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("TemplateLiteral", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-tagged-templates-runtime-semantics-evaluation",
    "sec-tagged-templates",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MemberExpression "Evaluation")
  |  0:let tagRef = __x0__
  |  1:app __x1__ = (GetValue tagRef)
  |  1:let tagFunc = [? __x1__]
  |  2:let thisCall = this
  |  3:app __x2__ = (IsInTailPosition thisCall)
  |  3:let tailCall = __x2__
  |  4:app __x3__ = (EvaluateCall tagFunc tagRef TemplateLiteral tailCall)
  |  4:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _tagRef_ be the result of evaluating |MemberExpression|.""",
    """          1. Let _tagFunc_ be ? GetValue(_tagRef_).""",
    """          1. Let _thisCall_ be this |MemberExpression|.""",
    """          1. Let _tailCall_ be IsInTailPosition(_thisCall_).""",
    """          1. Return ? EvaluateCall(_tagFunc_, _tagRef_, |TemplateLiteral|, _tailCall_).""",
  )
}
