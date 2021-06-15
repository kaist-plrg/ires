package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Catch[1,0].CatchClauseEvaluation` extends Algo {
  val head = SyntaxDirectedHead("Catch", 1, 0, Rhs(List(Terminal("catch"), NonTerminal("Block", List(""), false)), None), "CatchClauseEvaluation", List(Param("thrownValue", Normal)))
  val ids = List(
    "sec-runtime-semantics-catchclauseevaluation",
    "sec-try-statement",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of evaluating |Block|.""",
  )
}
