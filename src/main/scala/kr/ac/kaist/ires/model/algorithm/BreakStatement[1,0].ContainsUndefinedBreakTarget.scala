package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakStatement[1,0].ContainsUndefinedBreakTarget` extends Algo {
  val head = SyntaxDirectedHead("BreakStatement", 1, 0, Rhs(List(Terminal("break"), NonTerminal("LabelIdentifier", List(""), false), Terminal(";")), None), "ContainsUndefinedBreakTarget", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedbreaktarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:if (! (contains labelSet __x0__)) return true else 1:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the StringValue of |LabelIdentifier| is not an element of _labelSet_, return *true*.""",
    """        1. Return *false*.""",
  )
}
