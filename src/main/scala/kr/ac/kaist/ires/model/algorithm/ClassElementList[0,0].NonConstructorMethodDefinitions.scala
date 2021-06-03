package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[0,0].NonConstructorMethodDefinitions` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 0, 0, Rhs(List(NonTerminal("ClassElement", List(""), false)), None), "NonConstructorMethodDefinitions", List())
  val ids = List(
    "sec-static-semantics-nonconstructormethoddefinitions",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElement "ClassElementKind")
  |  0:if (= __x0__ CONST_NonConstructorMethod) return (new [ClassElement]) else 33:{}
  |  2:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If ClassElementKind of |ClassElement| is ~NonConstructorMethod~, then""",
    """          1. Return a List whose sole element is |ClassElement|.""",
    """        1. Return a new empty List.""",
  )
}
