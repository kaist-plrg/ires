package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Statement[11,0].LabelledEvaluation` extends Algo {
  val head = SyntaxDirectedHead("Statement", 11, 0, Rhs(List(NonTerminal("ThrowStatement", List(""), false)), None), "LabelledEvaluation", List(Param("labelSet", Normal)))
  val ids = List(
    "sec-runtime-semantics-labelledevaluation",
    "sec-labelled-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Statement "Evaluation")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of evaluating |Statement|.""",
  )
}
