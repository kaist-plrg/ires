package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectAssignmentPattern[3,1].DestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ObjectAssignmentPattern", 3, 1, Rhs(List(Terminal("{"), NonTerminal("AssignmentPropertyList", List(""), false), Terminal(","), NonTerminal("AssignmentRestProperty", List(""), false), Terminal("}")), None), "DestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-destructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (RequireObjectCoercible value)
  |  0:[? __x0__]
  |  1:access __x1__ = (AssignmentPropertyList "PropertyDestructuringAssignmentEvaluation" value)
  |  1:let excludedNames = [? __x1__]
  |  2:access __x2__ = (AssignmentRestProperty "RestDestructuringAssignmentEvaluation" value excludedNames)
  |  2:return __x2__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? RequireObjectCoercible(_value_).""",
    """          1. Let _excludedNames_ be ? PropertyDestructuringAssignmentEvaluation of |AssignmentPropertyList| with argument _value_.""",
    """          1. Return the result of performing RestDestructuringAssignmentEvaluation of |AssignmentRestProperty| with arguments _value_ and _excludedNames_.""",
  )
}
