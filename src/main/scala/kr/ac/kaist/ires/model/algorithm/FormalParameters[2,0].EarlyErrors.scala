package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameters[2,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("FormalParameters", 2, 0, Rhs(List(NonTerminal("FormalParameterList", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-parameter-lists-static-semantics-early-errors",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:let __x0__ = true
  |  0:access __x1__ = (FormalParameterList "IsSimpleParameterList")
  |  0:__x0__ = (= __x1__ false)
  |  0:if __x0__ {
  |    access __x2__ = (FormalParameterList "BoundNames")
  |    app __x3__ = (IsDuplicate __x2__)
  |    __x0__ = __x3__
  |  } else 2:{}
  |  0:if __x0__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if IsSimpleParameterList of |FormalParameterList| is *false* and BoundNames of |FormalParameterList| contains any duplicate elements.""",
    """        </li>""",
  )
}
