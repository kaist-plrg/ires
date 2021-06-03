package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportDeclaration[1,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ImportDeclaration", 1, 0, Rhs(List(Terminal("import"), NonTerminal("ModuleSpecifier", List(""), false), Terminal(";")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new [])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return a new empty List.""",
  )
}
