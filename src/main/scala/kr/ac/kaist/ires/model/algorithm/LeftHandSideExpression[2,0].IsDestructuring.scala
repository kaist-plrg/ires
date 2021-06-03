package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LeftHandSideExpression[2,0].IsDestructuring` extends Algo {
  val head = SyntaxDirectedHead("LeftHandSideExpression", 2, 0, Rhs(List(NonTerminal("OptionalExpression", List(""), false)), None), "IsDestructuring", List())
  val ids = List(
    "sec-static-semantics-isdestructuring",
    "sec-for-in-and-for-of-statements",
    "sec-iteration-statements",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""return false""".stripMargin)
  val code = scala.Array[String](
    """          1. Return *false*.""",
  )
}
