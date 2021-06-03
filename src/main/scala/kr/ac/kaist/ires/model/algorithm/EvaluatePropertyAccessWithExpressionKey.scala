package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EvaluatePropertyAccessWithExpressionKey` extends Algo {
  val head = NormalHead("EvaluatePropertyAccessWithExpressionKey", List(Param("baseValue", Normal), Param("expression", Normal), Param("strict", Normal)))
  val ids = List(
    "sec-evaluate-property-access-with-expression-key",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (expression "Evaluation")
  |  0:let propertyNameReference = __x0__
  |  1:app __x1__ = (GetValue propertyNameReference)
  |  1:let propertyNameValue = [? __x1__]
  |  2:app __x2__ = (RequireObjectCoercible baseValue)
  |  2:let bv = [? __x2__]
  |  3:app __x3__ = (ToPropertyKey propertyNameValue)
  |  3:let propertyKey = [? __x3__]
  |  4:return (new ReferenceRecord("Base" -> bv, "ReferencedName" -> propertyKey, "Strict" -> strict, "ThisValue" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _propertyNameReference_ be the result of evaluating _expression_.""",
    """        1. Let _propertyNameValue_ be ? GetValue(_propertyNameReference_).""",
    """        1. Let _bv_ be ? RequireObjectCoercible(_baseValue_).""",
    """        1. Let _propertyKey_ be ? ToPropertyKey(_propertyNameValue_).""",
    """        1. Return the Reference Record { [[Base]]: _bv_, [[ReferencedName]]: _propertyKey_, [[Strict]]: _strict_, [[ThisValue]]: ~empty~ }.""",
  )
}
