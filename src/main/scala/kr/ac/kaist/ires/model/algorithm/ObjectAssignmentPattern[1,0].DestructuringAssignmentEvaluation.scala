package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectAssignmentPattern[1,0].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ObjectAssignmentPattern", 1, 0, Rhs(List(Terminal("{"), NonTerminal("AssignmentRestProperty", List(""), false), Terminal("}")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible value)
  |  0:[? __x0__]
  |  1:let excludedNames = (new [])
  |  2:access __x1__ = (AssignmentRestProperty "RestDestructuringAssignmentEvaluation" value excludedNames)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireObjectCoercible(_value_).""",
    """          1. Let _excludedNames_ be a new empty List.""",
    """          1. Return the result of performing RestDestructuringAssignmentEvaluation of |AssignmentRestProperty| with _value_ and _excludedNames_ as the arguments.""",
  )
}
