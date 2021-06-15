package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentList[0,0].ArgumentListEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ArgumentList", 0, 0, Rhs(List(NonTerminal("AssignmentExpression", List(""), false)), None), "ArgumentListEvaluation", List())
  val ids = List(
    "sec-runtime-semantics-argumentlistevaluation",
    "sec-argument-lists",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let ref = __x0__
  |  1:app __x1__ = (GetValue ref)
  |  1:let arg = [? __x1__]
  |  2:return (new [arg])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _ref_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _arg_ be ? GetValue(_ref_).""",
    """          1. Return a List whose sole element is _arg_.""",
  )
}
