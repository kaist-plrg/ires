package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportDeclaration[0,0].ModuleRequests` extends Algo {
  val head = SyntaxDirectedHead("ImportDeclaration", 0, 0, Rhs(List(Terminal("import"), NonTerminal("ImportClause", List(""), false), NonTerminal("FromClause", List(""), false), Terminal(";")), None), "ModuleRequests", List())
  val ids = List(
    "sec-static-semantics-modulerequests",
    "sec-module-semantics",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FromClause "ModuleRequests")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Return ModuleRequests of |FromClause|.""",
  )
}
