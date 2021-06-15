package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledItem[1,0].TopLevelVarScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("LabelledItem", 1, 0, Rhs(List(NonTerminal("FunctionDeclaration", List(""), false)), None), "TopLevelVarScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevelvarscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [FunctionDeclaration])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a List whose sole element is |FunctionDeclaration|.""",
  )
}
