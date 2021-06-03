package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[4,0].PropertyDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 4, 0, Rhs(List(Terminal("..."), NonTerminal("AssignmentExpression", List(""), false)), None), "PropertyDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydefinitionevaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AssignmentExpression "Evaluation")
  |  0:let exprValue = __x0__
  |  1:app __x1__ = (GetValue exprValue)
  |  1:let fromValue = [? __x1__]
  |  2:let excludedNames = (new [])
  |  3:app __x2__ = (CopyDataProperties object fromValue excludedNames)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _exprValue_ be the result of evaluating |AssignmentExpression|.""",
    """          1. Let _fromValue_ be ? GetValue(_exprValue_).""",
    """          1. Let _excludedNames_ be a new empty List.""",
    """          1. Return ? CopyDataProperties(_object_, _fromValue_, _excludedNames_).""",
  )
}
