package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LiteralPropertyName[0,0].PropName` extends Algo {
  val head = SyntaxDirectedHead("LiteralPropertyName", 0, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false)), None), "PropName", List())
  val ids = List(
    "sec-static-semantics-propname",
    "sec-syntax-directed-operations-miscellaneous",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName "StringValue")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return StringValue of |IdentifierName|.""",
  )
}
