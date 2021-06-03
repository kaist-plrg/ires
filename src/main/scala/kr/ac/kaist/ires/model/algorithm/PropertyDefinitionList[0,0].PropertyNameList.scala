package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinitionList[0,0].PropertyNameList` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinitionList", 0, 0, Rhs(List(NonTerminal("PropertyDefinition", List(""), false)), None), "PropertyNameList", List())
  val ids = List(
    "sec-static-semantics-propertynamelist",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyDefinition "PropName")
  |  0:if (= __x0__ CONST_empty) return (new []) else 2:{}
  |  1:access __x1__ = (PropertyDefinition "PropName")
  |  1:return (new [__x1__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If PropName of |PropertyDefinition| is ~empty~, return a new empty List.""",
    """          1. Return a List whose sole element is PropName of |PropertyDefinition|.""",
  )
}
