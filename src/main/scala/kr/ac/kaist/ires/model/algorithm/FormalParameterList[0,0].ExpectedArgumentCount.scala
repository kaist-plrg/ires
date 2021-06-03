package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameterList[0,0].ExpectedArgumentCount` extends Algo {
  val head = SyntaxDirectedHead("FormalParameterList", 0, 0, Rhs(List(NonTerminal("FormalParameter", List(""), false)), None), "ExpectedArgumentCount", List())
  val ids = List(
    "sec-static-semantics-expectedargumentcount",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameter "HasInitializer")
  |  0:if (= __x0__ true) return 0i else 0:{}
  |  1:return 1i
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If HasInitializer of |FormalParameter| is *true*, return 0.""",
    """        1. Return 1.""",
  )
}
