package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassExpression[0,1].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassExpression", 0, 1, Rhs(List(Terminal("class"), NonTerminal("BindingIdentifier", List(""), false), NonTerminal("ClassTail", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-class-definitions-runtime-semantics-evaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingIdentifier "StringValue")
  |  0:let className = __x0__
  |  1:access __x1__ = (ClassTail "ClassDefinitionEvaluation" className className)
  |  1:let value = [? __x1__]
  |  2:value.SourceText = (get-syntax ClassExpression)
  |  3:return value
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _className_ be StringValue of |BindingIdentifier|.""",
    """        1. Let _value_ be ? ClassDefinitionEvaluation of |ClassTail| with arguments _className_ and _className_.""",
    """        1. Set _value_.[[SourceText]] to the source text matched by |ClassExpression|.""",
    """        1. Return _value_.""",
  )
}
