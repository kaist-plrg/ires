package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[2,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 2, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Catch", List(""), false), NonTerminal("Finally", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-try-statement-runtime-semantics-evaluation",
    "sec-try-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "Evaluation")
  |  0:let B = __x0__
  |  2:if (= B.Type CONST_throw) {
  |    access __x1__ = (Catch "CatchClauseEvaluation" B.Value)
  |    let C = __x1__
  |  } else let C = B
  |  3:access __x2__ = (Finally "Evaluation")
  |  3:let F = __x2__
  |  4:if (= F.Type CONST_normal) F = C else 0:{}
  |  5:app __x3__ = (UpdateEmpty F undefined)
  |  5:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _B_ be the result of evaluating |Block|.""",
    """        1. If _B_.[[Type]] is ~throw~, let _C_ be CatchClauseEvaluation of |Catch| with argument _B_.[[Value]].""",
    """        1. Else, let _C_ be _B_.""",
    """        1. Let _F_ be the result of evaluating |Finally|.""",
    """        1. If _F_.[[Type]] is ~normal~, set _F_ to _C_.""",
    """        1. Return Completion(UpdateEmpty(_F_, *undefined*)).""",
  )
}
