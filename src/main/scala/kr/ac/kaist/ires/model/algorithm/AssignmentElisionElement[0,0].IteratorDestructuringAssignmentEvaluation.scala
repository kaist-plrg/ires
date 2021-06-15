package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentElisionElement[0,0].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentElisionElement", 0, 0, Rhs(List(NonTerminal("AssignmentElement", List(""), false)), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentElement "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return the result of performing IteratorDestructuringAssignmentEvaluation of |AssignmentElement| with _iteratorRecord_ as the argument.""",
  )
}
