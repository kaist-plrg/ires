package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DebuggerStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("DebuggerStatement", 0, 0, Rhs(List(Terminal("debugger"), Terminal(";")), None), "Evaluation", List())
  val ids = List(
    "sec-debugger-statement-runtime-semantics-evaluation",
    "sec-debugger-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:??? "If an implementation - defined debugging facility is available and enabled , then in:{} out:{}"
  |  3:??? "Else , in:{} out:{}"
  |  5:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If an implementation-defined debugging facility is available and enabled, then""",
    """          1. Perform an implementation-defined debugging action.""",
    """          1. Let _result_ be an implementation-defined Completion value.""",
    """        1. Else,""",
    """          1. Let _result_ be NormalCompletion(~empty~).""",
    """        1. Return _result_.""",
  )
}
