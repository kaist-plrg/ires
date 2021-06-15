package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::StatementListItem[0,0].LexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("StatementListItem", 0, 0, Rhs(List(NonTerminal("Statement", List(""), false)), None), "LexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-lexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of Statement Statement10) {
  |    access LabelledStatement = (Statement "LabelledStatement")
  |    access __x0__ = (LabelledStatement "LexicallyScopedDeclarations")
  |    return __x0__
  |  } else 1:{}
  |  1:return (new [])
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Statement| is <emu-grammar>Statement : LabelledStatement</emu-grammar> , return LexicallyScopedDeclarations of |LabelledStatement|.""",
    """        1. Return a new empty List.""",
  )
}
