package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinitionList[1,0].PropertyDefinitionEvaluation` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinitionList", 1, 0, Rhs(List(NonTerminal("PropertyDefinitionList", List(""), false), Terminal(","), NonTerminal("PropertyDefinition", List(""), false)), None), "PropertyDefinitionEvaluation", List(Param("object", Normal), Param("enumerable", Normal)))
  val ids = List(
    "sec-runtime-semantics-propertydefinitionevaluation",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyDefinitionList "PropertyDefinitionEvaluation" object enumerable)
  |  0:[? __x0__]
  |  1:access __x1__ = (PropertyDefinition "PropertyDefinitionEvaluation" object enumerable)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Perform ? PropertyDefinitionEvaluation of |PropertyDefinitionList| with arguments _object_ and _enumerable_.""",
    """          1. Return the result of performing PropertyDefinitionEvaluation of |PropertyDefinition| with arguments _object_ and _enumerable_.""",
  )
}
