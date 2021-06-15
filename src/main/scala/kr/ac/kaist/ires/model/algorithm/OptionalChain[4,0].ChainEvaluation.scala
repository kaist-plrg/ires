package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalChain[4,0].ChainEvaluation` extends Algo {
  val head = SyntaxDirectedHead("OptionalChain", 4, 0, Rhs(List(NonTerminal("OptionalChain", List(""), false), NonTerminal("Arguments", List(""), false)), None), "ChainEvaluation", List(Param("baseValue", Normal), Param("baseReference", Normal)))
  val ids = List(
    "sec-optional-chaining-chain-evaluation",
    "sec-optional-chains",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let optionalChain = OptionalChain
  |  1:access __x0__ = (optionalChain "ChainEvaluation" baseValue baseReference)
  |  1:let newReference = [? __x0__]
  |  2:app __x1__ = (GetValue newReference)
  |  2:let newValue = [? __x1__]
  |  3:let thisChain = this
  |  4:app __x2__ = (IsInTailPosition thisChain)
  |  4:let tailCall = __x2__
  |  5:app __x3__ = (EvaluateCall newValue newReference Arguments tailCall)
  |  5:return [? __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _optionalChain_ be |OptionalChain|.""",
    """          1. Let _newReference_ be ? ChainEvaluation of _optionalChain_ with arguments _baseValue_ and _baseReference_.""",
    """          1. Let _newValue_ be ? GetValue(_newReference_).""",
    """          1. Let _thisChain_ be this |OptionalChain|.""",
    """          1. Let _tailCall_ be IsInTailPosition(_thisChain_).""",
    """          1. Return ? EvaluateCall(_newValue_, _newReference_, |Arguments|, _tailCall_).""",
  )
}
