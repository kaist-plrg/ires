package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::EvaluatePropertyAccessWithIdentifierKey` extends Algo {
  val head = NormalHead("EvaluatePropertyAccessWithIdentifierKey", List(Param("baseValue", Normal), Param("identifierName", Normal), Param("strict", Normal)))
  val ids = List(
    "sec-evaluate-property-access-with-identifier-key",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of identifierName IdentifierName)
  |  1:app __x0__ = (RequireObjectCoercible baseValue)
  |  1:let bv = [? __x0__]
  |  2:access __x1__ = (identifierName "StringValue")
  |  2:let propertyNameString = __x1__
  |  3:return (new ReferenceRecord("Base" -> bv, "ReferencedName" -> propertyNameString, "Strict" -> strict, "ThisValue" -> CONST_empty))
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _identifierName_ is an |IdentifierName|.""",
    """        1. Let _bv_ be ? RequireObjectCoercible(_baseValue_).""",
    """        1. Let _propertyNameString_ be StringValue of _identifierName_.""",
    """        1. Return the Reference Record { [[Base]]: _bv_, [[ReferencedName]]: _propertyNameString_, [[Strict]]: _strict_, [[ThisValue]]: ~empty~ }.""",
  )
}
