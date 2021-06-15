package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalChain[5,0].ChainEvaluation` extends Algo {
  val head = SyntaxDirectedHead("OptionalChain", 5, 0, Rhs(List(NonTerminal("OptionalChain", List(""), false), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "ChainEvaluation", List(Param("baseValue", Normal), Param("baseReference", Normal)))
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
  |  3:if true let strict = true else let strict = false
  |  4:app __x2__ = (EvaluatePropertyAccessWithExpressionKey newValue Expression strict)
  |  4:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _optionalChain_ be |OptionalChain|.""",
    """          1. Let _newReference_ be ? ChainEvaluation of _optionalChain_ with arguments _baseValue_ and _baseReference_.""",
    """          1. Let _newValue_ be ? GetValue(_newReference_).""",
    """          1. If the code matched by this |OptionalChain| is strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """          1. Return ? EvaluatePropertyAccessWithExpressionKey(_newValue_, |Expression|, _strict_).""",
  )
}
