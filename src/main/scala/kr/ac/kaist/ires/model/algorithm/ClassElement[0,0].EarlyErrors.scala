package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 0, 0, Rhs(List(NonTerminal("MethodDefinition", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-class-definitions-static-semantics-early-errors",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:access __x1__ = (MethodDefinition "PropName")
  |  0:__x0__ = (! (= __x1__ "constructor"))
  |  0:if __x0__ {
  |    access __x2__ = (MethodDefinition "HasDirectSuper")
  |    __x0__ = (= __x2__ true)
  |  } else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |  1:let __x3__ = true
  |  1:access __x4__ = (MethodDefinition "PropName")
  |  1:__x3__ = (= __x4__ "constructor")
  |  1:if __x3__ {
  |    access __x5__ = (MethodDefinition "SpecialMethod")
  |    __x3__ = (= __x5__ true)
  |  } else 2:{}
  |  1:if __x3__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if PropName of |MethodDefinition| is not *"constructor"* and HasDirectSuper of |MethodDefinition| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if PropName of |MethodDefinition| is *"constructor"* and SpecialMethod of |MethodDefinition| is *true*.""",
    """        </li>""",
  )
}
