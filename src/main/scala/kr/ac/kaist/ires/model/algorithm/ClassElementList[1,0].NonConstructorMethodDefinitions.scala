package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElementList[1,0].NonConstructorMethodDefinitions` extends Algo {
  val head = SyntaxDirectedHead("ClassElementList", 1, 0, Rhs(List(NonTerminal("ClassElementList", List(""), false), NonTerminal("ClassElement", List(""), false)), None), "NonConstructorMethodDefinitions", List())
  val ids = List(
    "sec-static-semantics-nonconstructormethoddefinitions",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ClassElementList "NonConstructorMethodDefinitions")
  |  0:let list = __x0__
  |  1:access __x1__ = (ClassElement "ClassElementKind")
  |  1:if (= __x1__ CONST_NonConstructorMethod) append ClassElement -> list else 33:{}
  |  3:return list
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _list_ be NonConstructorMethodDefinitions of |ClassElementList|.""",
    """        1. If ClassElementKind of |ClassElement| is ~NonConstructorMethod~, then""",
    """          1. Append |ClassElement| to the end of _list_.""",
    """        1. Return _list_.""",
  )
}
