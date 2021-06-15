package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BreakStatement", 0, 0, Rhs(List(Terminal("break"), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-break-statement-runtime-semantics-evaluation",
    "sec-break-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return (new Completion("Type" -> CONST_break, "Value" -> CONST_empty, "Target" -> CONST_empty))""".stripMargin)
  val code = scala.Array[String](
    """        1. Return Completion { [[Type]]: ~break~, [[Value]]: ~empty~, [[Target]]: ~empty~ }.""",
  )
}
