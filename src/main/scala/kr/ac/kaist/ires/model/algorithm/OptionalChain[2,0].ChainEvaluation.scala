package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::OptionalChain[2,0].ChainEvaluation` extends Algo {
  val head = SyntaxDirectedHead("OptionalChain", 2, 0, Rhs(List(Terminal("?."), NonTerminal("IdentifierName", List(""), false)), None), "ChainEvaluation", List(Param("baseValue", Normal), Param("baseReference", Normal)))
  val ids = List(
    "sec-optional-chaining-chain-evaluation",
    "sec-optional-chains",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:if true let strict = true else let strict = false
  |  1:app __x0__ = (EvaluatePropertyAccessWithIdentifierKey baseValue IdentifierName strict)
  |  1:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If the code matched by this |OptionalChain| is strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """          1. Return ? EvaluatePropertyAccessWithIdentifierKey(_baseValue_, |IdentifierName|, _strict_).""",
  )
}
