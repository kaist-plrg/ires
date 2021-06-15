package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinitionList[1,0].PropertyNameList` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinitionList", 1, 0, Rhs(List(NonTerminal("PropertyDefinitionList", List(""), false), Terminal(","), NonTerminal("PropertyDefinition", List(""), false)), None), "PropertyNameList", List())
  val ids = List(
    "sec-static-semantics-propertynamelist",
    "sec-object-initializer",
    "sec-primary-expression",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyDefinitionList "PropertyNameList")
  |  0:let list = __x0__
  |  1:access __x1__ = (PropertyDefinition "PropName")
  |  1:if (= __x1__ CONST_empty) return list else 2:{}
  |  2:access __x2__ = (PropertyDefinition "PropName")
  |  2:append __x2__ -> list
  |  3:return list
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _list_ be PropertyNameList of |PropertyDefinitionList|.""",
    """          1. If PropName of |PropertyDefinition| is ~empty~, return _list_.""",
    """          1. Append PropName of |PropertyDefinition| to the end of _list_.""",
    """          1. Return _list_.""",
  )
}
