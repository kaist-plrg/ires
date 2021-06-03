package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[1,3].ForLoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 1, 3, Rhs(List(Terminal("for"), Terminal("("), Terminal("var"), NonTerminal("VariableDeclarationList", List(""), false), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ForLoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-forloopevaluation",
    "sec-for-statement",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (VariableDeclarationList "Evaluation")
  |  0:let varDcl = __x0__
  |  1:[? varDcl]
  |  2:app __x1__ = (ForBodyEvaluation Expression0 Expression1 Statement (new []) labelSet)
  |  2:return [? __x1__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _varDcl_ be the result of evaluating |VariableDeclarationList|.""",
    """          1. ReturnIfAbrupt(_varDcl_).""",
    """          1. Return ? ForBodyEvaluation(the first |Expression|, the second |Expression|, |Statement|, « », _labelSet_).""",
  )
}
