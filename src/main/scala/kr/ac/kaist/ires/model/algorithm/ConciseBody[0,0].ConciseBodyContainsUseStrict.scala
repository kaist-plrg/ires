package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ConciseBody[0,0].ConciseBodyContainsUseStrict` extends Algo {
  val head = SyntaxDirectedHead("ConciseBody", 0, 0, Rhs(List(NonTerminal("ExpressionBody", List(""), false)), None), "ConciseBodyContainsUseStrict", List())
  val ids = List(
    "sec-static-semantics-concisebodycontainsusestrict",
    "sec-arrow-function-definitions",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
