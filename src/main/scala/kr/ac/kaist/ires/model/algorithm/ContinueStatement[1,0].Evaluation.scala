package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ContinueStatement[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ContinueStatement", 1, 0, Rhs(List(Terminal("continue"), NonTerminal("LabelIdentifier", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-continue-statement-runtime-semantics-evaluation",
    "sec-continue-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:let label = __x0__
  |  1:return (new Completion("Type" -> CONST_continue, "Value" -> CONST_empty, "Target" -> label))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _label_ be the StringValue of |LabelIdentifier|.""",
    """        1. Return Completion { [[Type]]: ~continue~, [[Value]]: ~empty~, [[Target]]: _label_ }.""",
  )
}
