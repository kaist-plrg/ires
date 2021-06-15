package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Block[0,0].TopLevelLexicallyScopedDeclarations` extends Algo {
  val head = SyntaxDirectedHead("Block", 0, 0, Rhs(List(Terminal("{"), Terminal("}")), None), "TopLevelLexicallyScopedDeclarations", List())
  val ids = List(
    "sec-static-semantics-toplevellexicallyscopeddeclarations",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a new empty List.""",
  )
}
