package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[3,0].IsIdentifierRef` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 3, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), NonTerminal("TemplateLiteral", List(""), false)), None), "IsIdentifierRef", List())
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
