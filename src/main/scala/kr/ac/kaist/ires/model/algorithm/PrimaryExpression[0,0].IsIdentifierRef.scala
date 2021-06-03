package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PrimaryExpression[0,0].IsIdentifierRef` extends Algo {
  val head = SyntaxDirectedHead("PrimaryExpression", 0, 0, Rhs(List(Terminal("this")), None), "IsIdentifierRef", List())
  val ids = List(
    "sec-static-semantics-isidentifierref",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
