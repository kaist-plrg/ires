package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[0,0].ForInOfLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 0, 0, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("LeftHandSideExpression", List(""), false), Terminal("in"), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForInOfLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forinofloopevaluation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ForInOfHeadEvaluation (new []) Expression CONST_enumerate)
  |  0:let keyResult = [? __x0__]
  |  1:app __x1__ = (ForInOfBodyEvaluation LeftHandSideExpression Statement keyResult CONST_enumerate CONST_assignment labelSet)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keyResult_ be ? ForIn/OfHeadEvaluation(« », |Expression|, ~enumerate~).""",
    """          1. Return ? ForIn/OfBodyEvaluation(|LeftHandSideExpression|, |Statement|, _keyResult_, ~enumerate~, ~assignment~, _labelSet_).""",
  )
}
