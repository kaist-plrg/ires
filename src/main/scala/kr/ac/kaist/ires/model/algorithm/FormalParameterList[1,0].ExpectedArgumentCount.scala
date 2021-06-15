package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameterList[1,0].ExpectedArgumentCount` extends Algo {
  val head = SyntaxDirectedHead("FormalParameterList", 1, 0, Rhs(List(NonTerminal("FormalParameterList", List(""), false), Terminal(","), NonTerminal("FormalParameter", List(""), false)), None), "ExpectedArgumentCount", List())
  val ids = List(
    "sec-static-semantics-expectedargumentcount",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameterList "ExpectedArgumentCount")
  |  0:let count = __x0__
  |  1:let __x1__ = true
  |  1:access __x2__ = (FormalParameterList "HasInitializer")
  |  1:__x1__ = (= __x2__ true)
  |  1:if __x1__ 0:{} else {
  |    access __x3__ = (FormalParameter "HasInitializer")
  |    __x1__ = (= __x3__ true)
  |  }
  |  1:if __x1__ return count else 0:{}
  |  2:return (+ count 1i)
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _count_ be ExpectedArgumentCount of |FormalParameterList|.""",
    """        1. If HasInitializer of |FormalParameterList| is *true* or HasInitializer of |FormalParameter| is *true*, return _count_.""",
    """        1. Return _count_ + 1.""",
  )
}
