package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[1,0].IsFunctionDefinition` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 1, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "IsFunctionDefinition", List())
  val ids = List(
    "sec-static-semantics-isfunctiondefinition",
    "sec-syntax-directed-operations-function-name-inference",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """        1. Return *false*.""",
  )
}
