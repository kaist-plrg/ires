package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledItem[1,0].TopLevelVarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("LabelledItem", 1, 0, Rhs(List(NonTerminal("FunctionDeclaration", List(""), false)), None), "TopLevelVarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-toplevelvardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (FunctionDeclaration "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return BoundNames of |FunctionDeclaration|.""",
  )
}
