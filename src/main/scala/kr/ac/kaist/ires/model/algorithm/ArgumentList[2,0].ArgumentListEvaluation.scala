package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentList[2,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArgumentList", 2, 0, Rhs(List(NonTerminal("ArgumentList", List(""), false), Terminal(","), NonTerminal("AssignmentExpression", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ArgumentList "ArgumentListEvaluation")
  |  0:let precedingArgs = [? __x0__]
  |  1:access __x1__ = (AssignmentExpression "Evaluation")
  |  1:let ref = __x1__
  |  2:app __x2__ = (GetValue ref)
  |  2:let arg = [? __x2__]
  |  3:append arg -> precedingArgs
  |  4:return precedingArgs
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _precedingArgs_ be ? ArgumentListEvaluation of |ArgumentList|.""",
    """          1. Let _ref_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _arg_ be ? GetValue(_ref_).""",
    """          1. Append _arg_ to the end of _precedingArgs_.""",
    """          1. Return _precedingArgs_.""",
  )
}
