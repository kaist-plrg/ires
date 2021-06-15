package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[0,0].ClassElementKind` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 0, 0, Rhs(List(NonTerminal("MethodDefinition", List(""), false)), None), "ClassElementKind", List())
  val ids = List(
    "sec-static-semantics-classelementkind",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MethodDefinition "PropName")
  |  0:if (= __x0__ "constructor") return CONST_ConstructorMethod else 33:{}
  |  1:return CONST_NonConstructorMethod
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If PropName of |MethodDefinition| is *"constructor"*, return ~ConstructorMethod~.""",
    """        1. Return ~NonConstructorMethod~.""",
  )
}
