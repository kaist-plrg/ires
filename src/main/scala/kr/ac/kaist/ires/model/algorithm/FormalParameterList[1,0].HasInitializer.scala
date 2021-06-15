package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameterList[1,0].HasInitializer` extends Algo {
  val head = SyntaxDirectedHead("FormalParameterList", 1, 0, Rhs(List(NonTerminal("FormalParameterList", List(""), false), Terminal(","), NonTerminal("FormalParameter", List(""), false)), None), "HasInitializer", List())
  val ids = List(
    "sec-static-semantics-hasinitializer",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameterList "HasInitializer")
  |  0:if (= __x0__ true) return true else 0:{}
  |  1:access __x1__ = (FormalParameter "HasInitializer")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If HasInitializer of |FormalParameterList| is *true*, return *true*.""",
    """        1. Return HasInitializer of |FormalParameter|.""",
  )
}
