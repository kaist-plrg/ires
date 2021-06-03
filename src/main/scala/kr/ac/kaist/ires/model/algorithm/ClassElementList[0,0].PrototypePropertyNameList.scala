package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[0,0].PrototypePropertyNameList` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 0, 0, Rhs(List(NonTerminal("ClassElement", List(""), false)), None), "PrototypePropertyNameList", List())
  val ids = List(
    "sec-static-semantics-prototypepropertynamelist",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElement "PropName")
  |  0:if (= __x0__ CONST_empty) return (new []) else 33:{}
  |  1:access __x1__ = (ClassElement "IsStatic")
  |  1:if (= __x1__ true) return (new []) else 33:{}
  |  2:access __x2__ = (ClassElement "PropName")
  |  2:return (new [__x2__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If PropName of |ClassElement| is ~empty~, return a new empty List.""",
    """        1. If IsStatic of |ClassElement| is *true*, return a new empty List.""",
    """        1. Return a List whose sole element is PropName of |ClassElement|.""",
  )
}
