package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FormalParameters[4,0].ExpectedArgumentCount` extends Algo {
  val head = SyntaxDirectedHead("FormalParameters", 4, 0, Rhs(List(NonTerminal("FormalParameterList", List(""), false), Terminal(","), NonTerminal("FunctionRestParameter", List(""), false)), None), "ExpectedArgumentCount", List())
  val ids = List(
    "sec-static-semantics-expectedargumentcount",
    "sec-parameter-lists",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FormalParameterList "ExpectedArgumentCount")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return ExpectedArgumentCount of |FormalParameterList|.""",
  )
}
