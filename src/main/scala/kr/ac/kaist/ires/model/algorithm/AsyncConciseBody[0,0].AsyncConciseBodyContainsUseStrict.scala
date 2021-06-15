package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncConciseBody[0,0].AsyncConciseBodyContainsUseStrict` extends Algo {
  val head = SyntaxDirectedHead("AsyncConciseBody", 0, 0, Rhs(List(NonTerminal("ExpressionBody", List(""), false)), None), "AsyncConciseBodyContainsUseStrict", List())
  val ids = List(
    "sec-static-semantics-asyncconcisebodycontainsusestrict",
    "sec-async-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
