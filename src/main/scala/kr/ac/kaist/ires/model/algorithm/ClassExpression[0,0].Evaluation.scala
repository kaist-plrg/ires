package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassExpression[0,0].Evaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassExpression", 0, 0, Rhs(List(Terminal("class"), NonTerminal("ClassTail", List(""), false)), None), "Evaluation", List())
  val ids = List(
    "sec-class-definitions-runtime-semantics-evaluation",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassTail "ClassDefinitionEvaluation" undefined "")
  |  0:let value = [? __x0__]
  |  1:value.SourceText = (get-syntax ClassExpression)
  |  2:return value
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _value_ be ? ClassDefinitionEvaluation of |ClassTail| with arguments *undefined* and *""*.""",
    """        1. Set _value_.[[SourceText]] to the source text matched by |ClassExpression|.""",
    """        1. Return _value_.""",
  )
}
