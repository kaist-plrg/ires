package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[8,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 8, 0, Rhs(List(Terminal("for"), Terminal("await"), Terminal("("), NonTerminal("ForDeclaration", List(""), false), Terminal("of"), NonTerminal("AssignmentExpression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
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
