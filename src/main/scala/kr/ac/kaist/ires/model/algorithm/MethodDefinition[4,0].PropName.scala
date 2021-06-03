package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MethodDefinition[4,0].PropName` extends Algo {
  val head = SyntaxDirectedHead("MethodDefinition", 4, 0, Rhs(List(Terminal("get"), NonTerminal("PropertyName", List(""), false), Terminal("("), Terminal(")"), Terminal("{"), NonTerminal("FunctionBody", List(""), false), Terminal("}")), None), "PropName", List())
  val ids = List(
    "sec-static-semantics-propname",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (PropertyName "PropName")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return PropName of |PropertyName|.""",
  )
}
