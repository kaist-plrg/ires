package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ModuleItem[2,0].ImportEntries` extends Algo {
  val head = SyntaxDirectedHead("ModuleItem", 2, 0, Rhs(List(NonTerminal("StatementListItem", List(""), false)), None), "ImportEntries", List())
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
