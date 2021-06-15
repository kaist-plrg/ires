package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Identifier[0,0].StringValue` extends Algo {
  val head = SyntaxDirectedHead("Identifier", 0, 0, Rhs(List(ButNot(NonTerminal("IdentifierName", List(""), false), List(NonTerminal("ReservedWord", List(""), false)))), None), "StringValue", List())
  val ids = List(
    "sec-static-semantics-stringvalue",
    "sec-identifiers",
    "sec-ecmascript-language-expressions",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName "StringValue")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the StringValue of |IdentifierName|.""",
  )
}
