package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleSpecifier[0,0].ModuleRequests` extends Algo {
  val head = SyntaxDirectedHead("ModuleSpecifier", 0, 0, Rhs(List(NonTerminal("StringLiteral", List(""), false)), None), "ModuleRequests", List())
  val ids = List(
    "sec-static-semantics-modulerequests",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StringLiteral "SV")
  |  0:return (new [__x0__])
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a List whose sole element is the SV of |StringLiteral|.""",
  )
}
