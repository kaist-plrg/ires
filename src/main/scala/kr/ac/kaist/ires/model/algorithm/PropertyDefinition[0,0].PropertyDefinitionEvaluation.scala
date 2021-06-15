package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[0,0].PropertyDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 0, 0, Rhs(List(NonTerminal("IdentifierReference", List(""), false)), None), "PropertyDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydefinitionevaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierReference "StringValue")
  |  0:let propName = __x0__
  |  1:access __x1__ = (IdentifierReference "Evaluation")
  |  1:let exprValue = __x1__
  |  2:app __x2__ = (GetValue exprValue)
  |  2:let propValue = [? __x2__]
  |  3:assert (= enumerable true)
  |  5:app __x3__ = (CreateDataPropertyOrThrow object propName propValue)
  |  5:return [! __x3__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _propName_ be StringValue of |IdentifierReference|.""",
    """          1. Let _exprValue_ be the result of evaluating |IdentifierReference|.""",
    """          1. Let _propValue_ be ? GetValue(_exprValue_).""",
    """          1. Assert: _enumerable_ is *true*.""",
    """          1. Assert: _object_ is an ordinary, extensible object with no non-configurable properties.""",
    """          1. Return ! CreateDataPropertyOrThrow(_object_, _propName_, _propValue_).""",
  )
}
