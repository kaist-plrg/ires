package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncConciseBody[1,0].AsyncConciseBodyContainsUseStrict` extends Algo {
  val head = SyntaxDirectedHead("AsyncConciseBody", 1, 0, Rhs(List(Terminal("{"), NonTerminal("AsyncFunctionBody", List(""), false), Terminal("}")), None), "AsyncConciseBodyContainsUseStrict", List())
  val ids = List(
    "sec-static-semantics-asyncconcisebodycontainsusestrict",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (AsyncFunctionBody "FunctionBodyContainsUseStrict")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return FunctionBodyContainsUseStrict of |AsyncFunctionBody|.""",
  )
}
