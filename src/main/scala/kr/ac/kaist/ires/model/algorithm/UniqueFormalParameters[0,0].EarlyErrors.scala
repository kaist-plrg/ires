package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::UniqueFormalParameters[0,0].EarlyErrors` extends Algo {
  val head = SyntaxDirectedHead("UniqueFormalParameters", 0, 0, Rhs(List(NonTerminal("FormalParameters", List(""), false)), None), "EarlyErrors", List())
  val ids = List(
    "sec-parameter-lists-static-semantics-early-errors",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameters "BoundNames")
  |  0:app __x1__ = (IsDuplicate __x0__)
  |  0:if __x1__ throw SyntaxError else 2:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """        <li>""",
    """          It is a Syntax Error if BoundNames of |FormalParameters| contains any duplicate elements.""",
    """        </li>""",
  )
}
