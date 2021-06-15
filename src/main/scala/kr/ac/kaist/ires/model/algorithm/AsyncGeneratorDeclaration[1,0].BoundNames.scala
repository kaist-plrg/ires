package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncGeneratorDeclaration[1,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("AsyncGeneratorDeclaration", 1, 0, Rhs(List(Terminal("async"), Terminal("function"), Terminal("*"), Terminal("("), NonTerminal("FormalParameters", List(""), false), Terminal(")"), Terminal("{"), NonTerminal("AsyncGeneratorBody", List(""), false), Terminal("}")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""return (new ["*default*"])""".stripMargin)
  val code = scala.Array[String](
    """        1. Return « *"\*default\*"* ».""",
  )
}
