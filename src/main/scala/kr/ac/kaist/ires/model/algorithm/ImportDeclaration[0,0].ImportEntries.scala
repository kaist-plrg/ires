package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportDeclaration[0,0].ImportEntries` extends Algo {
  val head = SyntaxDirectedHead("ImportDeclaration", 0, 0, Rhs(List(Terminal("import"), NonTerminal("ImportClause", List(""), false), NonTerminal("FromClause", List(""), false), Terminal(";")), None), "ImportEntries", List())
  val ids = List(
    "sec-static-semantics-importentries",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FromClause "ModuleRequests")
  |  0:let module = __x0__[0i]
  |  1:access __x1__ = (ImportClause "ImportEntriesForModule" module)
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _module_ be the sole element of ModuleRequests of |FromClause|.""",
    """          1. Return ImportEntriesForModule of |ImportClause| with argument _module_.""",
  )
}
