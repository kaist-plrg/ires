package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CallExpression[4,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("CallExpression", 4, 0, Rhs(List(NonTerminal("CallExpression", List(""), false), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "Evaluation", List())
  val ids = List(
    "sec-property-accessors-runtime-semantics-evaluation",
    "sec-property-accessors",
    "sec-left-hand-side-expressions",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CallExpression "Evaluation")
  |  0:let baseReference = __x0__
  |  1:app __x1__ = (GetValue baseReference)
  |  1:let baseValue = [? __x1__]
  |  2:if true let strict = true else let strict = false
  |  3:app __x2__ = (EvaluatePropertyAccessWithExpressionKey baseValue Expression strict)
  |  3:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _baseReference_ be the result of evaluating |CallExpression|.""",
    """          1. Let _baseValue_ be ? GetValue(_baseReference_).""",
    """          1. If the code matched by this |CallExpression| is strict mode code, let _strict_ be *true*; else let _strict_ be *false*.""",
    """          1. Return ? EvaluatePropertyAccessWithExpressionKey(_baseValue_, |Expression|, _strict_).""",
  )
}
