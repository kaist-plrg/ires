package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementListItem[0,0].TopLevelLexicallyDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("StatementListItem", 0, 0, Rhs(List(NonTerminal("Statement", List(""), false)), None), "TopLevelLexicallyDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-toplevellexicallydeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a new empty List.""",
  )
}
