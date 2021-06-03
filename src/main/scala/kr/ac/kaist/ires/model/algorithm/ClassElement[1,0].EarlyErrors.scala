package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ClassElement[1,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("ClassElement", 1, 0, Rhs(List(Terminal("static"), NonTerminal("MethodDefinition", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-class-definitions-static-semantics-early-errors",
    "sec-class-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (MethodDefinition "HasDirectSuper")
  |  0:if (= __x0__ true) throw SyntaxError else 2:{}
  |  1:access __x1__ = (MethodDefinition "PropName")
  |  1:if (= __x1__ "prototype") throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if HasDirectSuper of |MethodDefinition| is *true*.""",
    """        </li>""",
    """        <li>""",
    """          It is a Syntax Error if PropName of |MethodDefinition| is *"prototype"*.""",
    """        </li>""",
  )
}
