package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentElementList[1,0].IteratorDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentElementList", 1, 0, Rhs(List(NonTerminal("AssignmentElementList", List(""), false), Terminal(","), NonTerminal("AssignmentElisionElement", List(""), false)), None), "IteratorDestructuringAssignmentEvaluation", List(Param("iteratorRecord", Normal)))
  val ids = List(
    "sec-runtime-semantics-iteratordestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentElementList "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  0:[? __x0__]
  |  1:access __x1__ = (AssignmentElisionElement "IteratorDestructuringAssignmentEvaluation" iteratorRecord)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? IteratorDestructuringAssignmentEvaluation of |AssignmentElementList| using _iteratorRecord_ as the argument.""",
    """          1. Return the result of performing IteratorDestructuringAssignmentEvaluation of |AssignmentElisionElement| using _iteratorRecord_ as the argument.""",
  )
}
