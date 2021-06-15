package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[0,0].ConstructorMethod` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 0, 0, Rhs(List(NonTerminal("ClassElement", List(""), false)), None), "ConstructorMethod", List())
  val ids = List(
    "sec-static-semantics-constructormethod",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElement "ClassElementKind")
  |  0:if (= __x0__ CONST_ConstructorMethod) return ClassElement else 33:{}
  |  1:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If ClassElementKind of |ClassElement| is ~ConstructorMethod~, return |ClassElement|.""",
    """        1. Return ~empty~.""",
  )
}
