package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[5,0].ForInOfLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 5, 0, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("ForDeclaration", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForInOfLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forinofloopevaluation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ForDeclaration "BoundNames")
  |  0:app __x1__ = (ForInOfHeadEvaluation __x0__ AssignmentExpression CONST_iterate)
  |  0:let keyResult = [? __x1__]
  |  1:app __x2__ = (ForInOfBodyEvaluation ForDeclaration Statement keyResult CONST_iterate CONST_lexicalBinding labelSet)
  |  1:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keyResult_ be ? ForIn/OfHeadEvaluation(BoundNames of |ForDeclaration|, |AssignmentExpression|, ~iterate~).""",
    """          1. Return ? ForIn/OfBodyEvaluation(|ForDeclaration|, |Statement|, _keyResult_, ~iterate~, ~lexicalBinding~, _labelSet_).""",
  )
}
