package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("OptionalExpression", 0, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("OptionalChain", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-optional-chaining-evaluation",
    "sec-optional-chains",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MemberExpression "Evaluation")
  |  0:let baseReference = __x0__
  |  1:app __x1__ = (GetValue baseReference)
  |  1:let baseValue = [? __x1__]
  |  2:if (|| (= baseValue undefined) (= baseValue null)) return undefined else 3:{}
  |  4:access __x2__ = (OptionalChain "ChainEvaluation" baseValue baseReference)
  |  4:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _baseReference_ be the result of evaluating |MemberExpression|.""",
    """          1. Let _baseValue_ be ? GetValue(_baseReference_).""",
    """          1. If _baseValue_ is *undefined* or *null*, then""",
    """            1. Return *undefined*.""",
    """          1. Return the result of performing ChainEvaluation of |OptionalChain| with arguments _baseValue_ and _baseReference_.""",
  )
}
