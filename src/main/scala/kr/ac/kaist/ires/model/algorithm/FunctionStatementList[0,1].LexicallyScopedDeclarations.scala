package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::FunctionStatementList[0,1].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("FunctionStatementList", 0, 1, Rhs(List(NonTerminal("StatementList", List(""), false)), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (StatementList "TopLevelLexicallyScopedDeclarations")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the TopLevelLexicallyScopedDeclarations of |StatementList|.""",
  )
}
