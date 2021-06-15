package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakStatement[1,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BreakStatement", 1, 0, Rhs(List(Terminal("break"), NonTerminal("LabelIdentifier", List(""), false), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-break-statement-runtime-semantics-evaluation",
    "sec-break-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelIdentifier "StringValue")
  |  0:let label = __x0__
  |  1:return (new Completion("Type" -> CONST_break, "Value" -> CONST_empty, "Target" -> label))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _label_ be the StringValue of |LabelIdentifier|.""",
    """        1. Return Completion { [[Type]]: ~break~, [[Value]]: ~empty~, [[Target]]: _label_ }.""",
  )
}
