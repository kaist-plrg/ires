package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MemberExpression[1,0].IsDestructuring` extends Algo {
  val head = SyntaxDirectedHead("MemberExpression", 1, 0, Rhs(List(NonTerminal("MemberExpression", List(""), false), Terminal("["), NonTerminal("Expression", List(""), false), Terminal("]")), None), "IsDestructuring", List())
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