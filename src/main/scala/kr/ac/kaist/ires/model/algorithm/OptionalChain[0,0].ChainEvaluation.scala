package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalChain[0,0].ChainEvaluation` extends Algo {
  val head = SyntaxDirectedHead("OptionalChain", 0, 0, Rhs(List(Terminal("?."), NonTerminal("Arguments", List(""), false)), None), "ChainEvaluation", List(Param("baseValue", Normal), Param("baseReference", Normal)))
  val ids = List(
    "sec-optional-chaining-chain-evaluation",
    "sec-optional-chains",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:let thisChain = this
  |  1:app __x0__ = (IsInTailPosition thisChain)
  |  1:let tailCall = __x0__
  |  2:app __x1__ = (EvaluateCall baseValue baseReference Arguments tailCall)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _thisChain_ be this |OptionalChain|.""",
    """          1. Let _tailCall_ be IsInTailPosition(_thisChain_).""",
    """          1. Return ? EvaluateCall(_baseValue_, _baseReference_, |Arguments|, _tailCall_).""",
  )
}
