package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentPropertyList[1,0].PropertyDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentPropertyList", 1, 0, Rhs(List(NonTerminal("AssignmentPropertyList", List(""), false), Terminal(","), NonTerminal("AssignmentProperty", List(""), false)), None), "PropertyDestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentPropertyList "PropertyDestructuringAssignmentEvaluation" value)
  |  0:let propertyNames = [? __x0__]
  |  1:access __x1__ = (AssignmentProperty "PropertyDestructuringAssignmentEvaluation" value)
  |  1:let nextNames = [? __x1__]
  |  2:let __x2__ = nextNames
  |  2:let __x3__ = 0i
  |  2:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> propertyNames
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  3:return propertyNames
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _propertyNames_ be ? PropertyDestructuringAssignmentEvaluation of |AssignmentPropertyList| with argument _value_.""",
    """          1. Let _nextNames_ be ? PropertyDestructuringAssignmentEvaluation of |AssignmentProperty| with argument _value_.""",
    """          1. Append each item in _nextNames_ to the end of _propertyNames_.""",
    """          1. Return _propertyNames_.""",
  )
}
