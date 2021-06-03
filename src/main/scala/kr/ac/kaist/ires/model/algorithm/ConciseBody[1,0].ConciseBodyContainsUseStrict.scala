package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConciseBody[1,0].ConciseBodyContainsUseStrict` extends Algo {
  val head = SyntaxDirectedHead("ConciseBody", 1, 0, Rhs(List(Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "ConciseBodyContainsUseStrict", List())
  val ids = List(
    "sec-static-semantics-concisebodycontainsusestrict",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionBody "FunctionBodyContainsUseStrict")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return FunctionBodyContainsUseStrict of |FunctionBody|.""",
  )
}
