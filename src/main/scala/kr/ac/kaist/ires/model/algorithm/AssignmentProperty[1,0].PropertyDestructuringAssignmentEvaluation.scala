package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentProperty[1,0].PropertyDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentProperty", 1, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal(":"), NonTerminal("AssignmentElement", List(""), false)), None), "PropertyDestructuringAssignmentEvaluation", List(Param("value", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let name = __x0__
  |  1:[? name]
  |  2:access __x1__ = (AssignmentElement "KeyedDestructuringAssignmentEvaluation" value name)
  |  2:[? __x1__]
  |  3:return (new [name])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _name_ be the result of evaluating |PropertyName|.""",
    """          1. ReturnIfAbrupt(_name_).""",
    """          1. Perform ? KeyedDestructuringAssignmentEvaluation of |AssignmentElement| with _value_ and _name_ as the arguments.""",
    """          1. Return a List whose sole element is _name_.""",
  )
}
