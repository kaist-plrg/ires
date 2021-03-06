package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportDeclaration[1,0].ImportEntries` extends Algo {
  val head = SyntaxDirectedHead("ImportDeclaration", 1, 0, Rhs(List(Terminal("import"), NonTerminal("ModuleSpecifier", List(""), false), Terminal(";")), None), "ImportEntries", List())
  val ids = List(
    "sec-static-semantics-importentries",
    "sec-imports",
    "sec-modules",
    "sec-ecmascript-language-scripts-and-modules",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """          1. Return a new empty List.""",
  )
}
