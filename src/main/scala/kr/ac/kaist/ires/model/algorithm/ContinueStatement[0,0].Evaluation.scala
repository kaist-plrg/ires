package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ContinueStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ContinueStatement", 0, 0, Rhs(List(Terminal("continue"), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-continue-statement-runtime-semantics-evaluation",
    "sec-continue-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return (new Completion("Type" -> CONST_continue, "Value" -> CONST_empty, "Target" -> CONST_empty))""".stripMargin)
  val code = scala.Array[String](
    """        1. Return Completion { [[Type]]: ~continue~, [[Value]]: ~empty~, [[Target]]: ~empty~ }.""",
  )
}
