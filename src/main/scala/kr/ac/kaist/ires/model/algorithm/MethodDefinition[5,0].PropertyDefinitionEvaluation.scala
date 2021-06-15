package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[5,0].PropertyDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 5, 0, Rhs(List(Terminal("set"), NonTerminal("PropertyName", List(""), false), Terminal("("), NonTerminal("PropertySetParameterList", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "PropertyDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydefinitionevaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MethodDefinition "MethodDefinitionEvaluation" object enumerable)
  |  0:return [? __x0__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ? MethodDefinitionEvaluation of |MethodDefinition| with arguments _object_ and _enumerable_.""",
  )
}
