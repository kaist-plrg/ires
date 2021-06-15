package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForStatement[2,3].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("ForStatement", 2, 3, Rhs(List(Terminal("for"), Terminal("("), NonTerminal("LexicalDeclaration", List(""), false), NonTerminal("Expression", List(""), true), Terminal(";"), NonTerminal("Expression", List(""), true), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "ContainsUndefinedContinueTarget" iterationSet (new []))
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsUndefinedContinueTarget of |Statement| with arguments _iterationSet_ and « ».""",
  )
}
