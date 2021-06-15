package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[2,0].PropertyDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 2, 0, Rhs(List(NonTerminal("PropertyName", List(""), false), Terminal(":"), NonTerminal("AssignmentExpression", List(""), false)), None), "PropertyDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydefinitionevaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "Evaluation")
  |  0:let propKey = __x0__
  |  1:[? propKey]
  |  4:app __x1__ = (IsAnonymousFunctionDefinition AssignmentExpression)
  |  4:if (= __x1__ true) {
  |    3:access __x2__ = (AssignmentExpression "NamedEvaluation" propKey)
  |    3:let propValue = [? __x2__]
  |  } else {
  |    5:access __x3__ = (AssignmentExpression "Evaluation")
  |    5:let exprValueRef = __x3__
  |    6:app __x4__ = (GetValue exprValueRef)
  |    6:let propValue = [? __x4__]
  |  }
  |  7:assert (= enumerable true)
  |  9:app __x5__ = (CreateDataPropertyOrThrow object propKey propValue)
  |  9:return [! __x5__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _propKey_ be the result of evaluating |PropertyName|.""",
    """          1. ReturnIfAbrupt(_propKey_).""",
    """          1. If IsAnonymousFunctionDefinition(|AssignmentExpression|) is *true*, then""",
    """            1. Let _propValue_ be ? NamedEvaluation of |AssignmentExpression| with argument _propKey_.""",
    """          1. Else,""",
    """            1. Let _exprValueRef_ be the result of evaluating |AssignmentExpression|.""",
    """            1. Let _propValue_ be ? GetValue(_exprValueRef_).""",
    """          1. Assert: _enumerable_ is *true*.""",
    """          1. Assert: _object_ is an ordinary, extensible object with no non-configurable properties.""",
    """          1. Return ! CreateDataPropertyOrThrow(_object_, _propKey_, _propValue_).""",
  )
}
