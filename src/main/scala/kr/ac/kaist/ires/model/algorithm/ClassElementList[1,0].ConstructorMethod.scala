package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[1,0].ConstructorMethod` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 1, 0, Rhs(List(NonTerminal("ClassElementList", List(""), false), NonTerminal("ClassElement", List(""), false)), None), "ConstructorMethod", List())
  val ids = List(
    "sec-static-semantics-constructormethod",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElementList "ConstructorMethod")
  |  0:let head = __x0__
  |  1:if (! (= head CONST_empty)) return head else 33:{}
  |  2:access __x1__ = (ClassElement "ClassElementKind")
  |  2:if (= __x1__ CONST_ConstructorMethod) return ClassElement else 33:{}
  |  3:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _head_ be ConstructorMethod of |ClassElementList|.""",
    """        1. If _head_ is not ~empty~, return _head_.""",
    """        1. If ClassElementKind of |ClassElement| is ~ConstructorMethod~, return |ClassElement|.""",
    """        1. Return ~empty~.""",
  )
}
