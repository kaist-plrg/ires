package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].HasName` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "HasName", List())
  val ids = List(
    "sec-static-semantics-hasname",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
