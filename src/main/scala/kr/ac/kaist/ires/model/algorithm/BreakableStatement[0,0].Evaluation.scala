package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BreakableStatement[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("BreakableStatement", 0, 0, Rhs(List(NonTerminal("IterationStatement", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-statement-semantics-runtime-semantics-evaluation",
    "sec-statement-semantics",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:let newLabelSet = (new [])
  |  1:access __x0__ = (this "LabelledEvaluation" newLabelSet)
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _newLabelSet_ be a new empty List.""",
    """        1. Return the result of performing LabelledEvaluation of this |BreakableStatement| with argument _newLabelSet_.""",
  )
}
