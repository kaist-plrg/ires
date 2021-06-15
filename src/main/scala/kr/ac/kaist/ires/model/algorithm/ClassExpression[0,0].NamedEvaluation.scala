package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassExpression[0,0].NamedEvaluation` extends Algo {
  val head = SyntaxDirectedHead("ClassExpression", 0, 0, Rhs(List(Terminal("class"), NonTerminal("ClassTail", List(""), false)), None), "NamedEvaluation", List(Param("name", Normal)))
  val ids = List(
    "sec-runtime-semantics-namedevaluation",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassTail "ClassDefinitionEvaluation" undefined name)
  |  0:let value = __x0__
  |  1:[? value]
  |  2:value.SourceText = (get-syntax ClassExpression)
  |  3:return value
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _value_ be the result of ClassDefinitionEvaluation of |ClassTail| with arguments *undefined* and _name_.""",
    """        1. ReturnIfAbrupt(_value_).""",
    """        1. Set _value_.[[SourceText]] to the source text matched by |ClassExpression|.""",
    """        1. Return _value_.""",
  )
}
