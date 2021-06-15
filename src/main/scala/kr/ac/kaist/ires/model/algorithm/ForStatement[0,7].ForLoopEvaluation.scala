package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[0,7].ForLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 0, 7, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forloopevaluation",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= Expression0 absent)) {
  |    1:access __x0__ = (Expression0 "Evaluation")
  |    1:let exprRef = __x0__
  |    2:app __x1__ = (GetValue exprRef)
  |    2:[? __x1__]
  |  } else 10:{}
  |  3:app __x2__ = (ForBodyEvaluation Expression1 Expression2 Statement (new []) labelSet)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If the first |Expression| is present, then""",
    """            1. Let _exprRef_ be the result of evaluating the first |Expression|.""",
    """            1. Perform ? GetValue(_exprRef_).""",
    """          1. Return ? ForBodyEvaluation(the second |Expression|, the third |Expression|, |Statement|, « », _labelSet_).""",
  )
}
