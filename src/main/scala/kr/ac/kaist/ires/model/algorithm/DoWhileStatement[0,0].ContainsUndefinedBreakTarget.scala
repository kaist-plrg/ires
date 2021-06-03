package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DoWhileStatement[0,0].ContainsUndefinedBreakTarget` extends Algo {
  val head = SyntaxDirectedHead("DoWhileStatement", 0, 0, Rhs(List(Terminal("do"), NonTerminal("Statement", List(""), false), Terminal("while"), Terminal("("), NonTerminal("Expression", List(""), false), Terminal(")"), Terminal(";")), None), "ContainsUndefinedBreakTarget", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedbreaktarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "ContainsUndefinedBreakTarget" labelSet)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsUndefinedBreakTarget of |Statement| with argument _labelSet_.""",
  )
}
