package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowParameters[1,0].IteratorBindingInitialization` extends Algo {
  val head = SyntaxDirectedHead("ArrowParameters", 1, 0, Rhs(List(NonTerminal("CoverParenthesizedExpressionAndArrowParameterList", List(""), false)), None), "IteratorBindingInitialization", List(Param("iteratorRecord", Normal), Param("environment", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratorbindinginitialization",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CoverParenthesizedExpressionAndArrowParameterList "CoveredFormalsList")
  |  0:let formals = __x0__
  |  1:access __x1__ = (formals "IteratorBindingInitialization" iteratorRecord environment)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _formals_ be CoveredFormalsList of |CoverParenthesizedExpressionAndArrowParameterList|.""",
    """        1. Return IteratorBindingInitialization of _formals_ with arguments _iteratorRecord_ and _environment_.""",
  )
}
