package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ForInOfStatement[1,0].ContainsDuplicateLabels` extends Algo {
  val head = SyntaxDirectedHead("ForInOfStatement", 1, 0, Rhs(List(Terminal("for"), Terminal("("), Terminal("var"), NonTerminal("ForBinding", List(""), false), Terminal("in"), NonTerminal("Expression", List(""), false), Terminal(")"), NonTerminal("Statement", List(""), false)), None), "ContainsDuplicateLabels", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsduplicatelabels",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "ContainsDuplicateLabels" labelSet)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ContainsDuplicateLabels of |Statement| with argument _labelSet_.""",
  )
}
