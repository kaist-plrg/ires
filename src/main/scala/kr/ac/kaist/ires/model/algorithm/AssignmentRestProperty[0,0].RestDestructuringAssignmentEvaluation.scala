package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AssignmentRestProperty[0,0].RestDestructuringAssignmentEvaluation` extends Algo {
  val head = SyntaxDirectedHead("AssignmentRestProperty", 0, 0, Rhs(List(Terminal("..."), NonTerminal("DestructuringAssignmentTarget", List(""), false)), None), "RestDestructuringAssignmentEvaluation", List(Param("value", Normal), Param("excludedNames", Normal)))
  val ids = List(
    "sec-runtime-semantics-restdestructuringassignmentevaluation",
    "sec-destructuring-assignment",
    "sec-assignment-operators",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (DestructuringAssignmentTarget "Evaluation")
  |  0:let lref = __x0__
  |  1:[? lref]
  |  2:app __x1__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  2:let restObj = [! __x1__]
  |  3:app __x2__ = (CopyDataProperties restObj value excludedNames)
  |  3:[? __x2__]
  |  4:app __x3__ = (PutValue lref restObj)
  |  4:return __x3__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _lref_ be the result of evaluating |DestructuringAssignmentTarget|.""",
    """          1. ReturnIfAbrupt(_lref_).""",
    """          1. Let _restObj_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Perform ? CopyDataProperties(_restObj_, _value_, _excludedNames_).""",
    """          1. Return PutValue(_lref_, _restObj_).""",
  )
}
