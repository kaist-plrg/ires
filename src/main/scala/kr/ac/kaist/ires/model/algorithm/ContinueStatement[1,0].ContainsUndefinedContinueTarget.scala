package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ContinueStatement[1,0].ContainsUndefinedContinueTarget` extends Algo {
  val head = SyntaxDirectedHead("ContinueStatement", 1, 0, Rhs(List(Terminal("continue"), NonTerminal("LabelIdentifier", List(""), false), Terminal(";")), None), "ContainsUndefinedContinueTarget", List(Param("iterationSet", Normal), Param("labelSet", Normal)))
  val ids = List(
    "sec-static-semantics-containsundefinedcontinuetarget",
    "sec-syntax-directed-operations-labels",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:if (! (contains iterationSet __x0__)) return true else 1:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the StringValue of |LabelIdentifier| is not an element of _iterationSet_, return *true*.""",
    """        1. Return *false*.""",
  )
}
