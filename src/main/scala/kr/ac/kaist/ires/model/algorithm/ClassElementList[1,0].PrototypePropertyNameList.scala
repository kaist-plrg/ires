package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[1,0].PrototypePropertyNameList` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 1, 0, Rhs(List(NonTerminal("ClassElementList", List(""), false), NonTerminal("ClassElement", List(""), false)), None), "PrototypePropertyNameList", List())
  val ids = List(
    "sec-static-semantics-prototypepropertynamelist",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElementList "PrototypePropertyNameList")
  |  0:let list = __x0__
  |  1:access __x1__ = (ClassElement "PropName")
  |  1:if (= __x1__ CONST_empty) return list else 33:{}
  |  2:access __x2__ = (ClassElement "IsStatic")
  |  2:if (= __x2__ true) return list else 33:{}
  |  3:access __x3__ = (ClassElement "PropName")
  |  3:append __x3__ -> list
  |  4:return list
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _list_ be PrototypePropertyNameList of |ClassElementList|.""",
    """        1. If PropName of |ClassElement| is ~empty~, return _list_.""",
    """        1. If IsStatic of |ClassElement| is *true*, return _list_.""",
    """        1. Append PropName of |ClassElement| to the end of _list_.""",
    """        1. Return _list_.""",
  )
}
