package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CaseClause[0,1].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("CaseClause", 0, 1, Rhs(List(Terminal("case"), NonTerminal("Expression", List(""), false), Terminal(":"), NonTerminal("StatementList", List(""), true)), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (= StatementList absent)) {
  |    access __x0__ = (StatementList "LexicallyScopedDeclarations")
  |    return __x0__
  |  } else 1:{}
  |  1:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If the |StatementList| is present, return the LexicallyScopedDeclarations of |StatementList|.""",
    """        1. Return a new empty List.""",
  )
}
