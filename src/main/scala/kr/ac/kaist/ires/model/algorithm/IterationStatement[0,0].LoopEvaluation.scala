package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IterationStatement[0,0].LoopEvaluation` extends Algo {
  val head = SyntaxDirectedHead("IterationStatement", 0, 0, Rhs(List(NonTerminal("DoWhileStatement", List(""), false)), None), "LoopEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-loopevaluation",
    "sec-iteration-statements-semantics",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (DoWhileStatement "DoWhileLoopEvaluation" labelSet)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? DoWhileLoopEvaluation of |DoWhileStatement| with argument _labelSet_.""",
  )
}
