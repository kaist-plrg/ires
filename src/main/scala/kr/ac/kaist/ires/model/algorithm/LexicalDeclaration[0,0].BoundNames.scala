package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::LexicalDeclaration[0,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("LexicalDeclaration", 0, 0, Rhs(List(NonTerminal("LetOrConst", List(""), false), NonTerminal("BindingList", List(""), false), Terminal(";")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingList "BoundNames")
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the BoundNames of |BindingList|.""",
  )
}
