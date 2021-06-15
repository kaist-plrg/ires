package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectAssignmentPattern[0,0].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ObjectAssignmentPattern", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible value)
  |  0:[? __x0__]
  |  1:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireObjectCoercible(_value_).""",
    """          1. Return NormalCompletion(~empty~).""",
  )
}
