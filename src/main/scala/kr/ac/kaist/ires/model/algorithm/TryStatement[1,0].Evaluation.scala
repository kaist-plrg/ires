package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 1, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Finally", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-try-statement-runtime-semantics-evaluation",
    "sec-try-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "Evaluation")
  |  0:let B = __x0__
  |  1:access __x1__ = (Finally "Evaluation")
  |  1:let F = __x1__
  |  2:if (= F.Type CONST_normal) F = B else 0:{}
  |  3:app __x2__ = (UpdateEmpty F undefined)
  |  3:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _B_ be the result of evaluating |Block|.""",
    """        1. Let _F_ be the result of evaluating |Finally|.""",
    """        1. If _F_.[[Type]] is ~normal~, set _F_ to _B_.""",
    """        1. Return Completion(UpdateEmpty(_F_, *undefined*)).""",
  )
}
