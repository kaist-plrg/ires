package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[7,0].ForInOfLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 7, 0, Rhs(List(Terminal("for"), Terminal("await"), Terminal("("), Terminal("var"), NonTerminal("ForBinding", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForInOfLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forinofloopevaluation",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ForInOfHeadEvaluation (new []) AssignmentExpression CONST_asyncDASHiterate)
  |  0:let keyResult = [? __x0__]
  |  1:app __x1__ = (ForInOfBodyEvaluation ForBinding Statement keyResult CONST_iterate CONST_varBinding labelSet CONST_async)
  |  1:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _keyResult_ be ? ForIn/OfHeadEvaluation(« », |AssignmentExpression|, ~async-iterate~).""",
    """          1. Return ? ForIn/OfBodyEvaluation(|ForBinding|, |Statement|, _keyResult_, ~iterate~, ~varBinding~, _labelSet_, ~async~).""",
  )
}
