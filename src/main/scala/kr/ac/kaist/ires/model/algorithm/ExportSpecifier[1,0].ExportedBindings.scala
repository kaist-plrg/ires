package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ExportSpecifier[1,0].ExportedBindings` extends Algo {
  val head = SyntaxDirectedHead("ExportSpecifier", 1, 0, Rhs(List(NonTerminal("IdentifierName", List(""), false), Terminal("as"), NonTerminal("IdentifierName", List(""), false)), None), "ExportedBindings", List())
  val ids = List(
    "sec-static-semantics-exportedbindings",
    "sec-exports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (IdentifierName0 "StringValue")
  |  0:return (new [__x0__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a List whose sole element is the StringValue of the first |IdentifierName|.""",
  )
}
