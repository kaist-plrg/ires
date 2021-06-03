package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledItem[0,0].TopLevelVarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("LabelledItem", 0, 0, Rhs(List(NonTerminal("Statement", List(""), false)), None), "TopLevelVarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevelvarscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of Statement Statement10) {
  |    access LabelledStatement = (Statement "LabelledStatement")
  |    access __x0__ = (Statement "TopLevelVarScopedDeclarations")
  |    return __x0__
  |  } else 1:{}
  |  1:access __x1__ = (Statement "VarScopedDeclarations")
  |  1:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |Statement| is <emu-grammar>Statement : LabelledStatement</emu-grammar> , return TopLevelVarScopedDeclarations of |Statement|.""",
    """        1. Return VarScopedDeclarations of |Statement|.""",
  )
}
