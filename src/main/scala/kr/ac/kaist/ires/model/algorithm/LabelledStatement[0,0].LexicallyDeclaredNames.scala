package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LabelledStatement[0,0].LexicallyDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("LabelledStatement", 0, 0, Rhs(List(NonTerminal("LabelIdentifier", List(""), false), Terminal(":"), NonTerminal("LabelledItem", List(""), false)), None), "LexicallyDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-lexicallydeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (LabelledItem "LexicallyDeclaredNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the LexicallyDeclaredNames of |LabelledItem|.""",
  )
}
