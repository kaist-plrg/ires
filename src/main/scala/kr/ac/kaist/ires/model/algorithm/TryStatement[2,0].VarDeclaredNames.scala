package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::TryStatement[2,0].VarDeclaredNames` extends Algo {
  val head = SyntaxDirectedHead("TryStatement", 2, 0, Rhs(List(Terminal("try"), NonTerminal("Block", List(""), false), NonTerminal("Catch", List(""), false), NonTerminal("Finally", List(""), false)), None), "VarDeclaredNames", List())
  val ids = List(
    "sec-static-semantics-vardeclarednames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (Block "VarDeclaredNames")
  |  0:let names = __x0__
  |  1:access __x1__ = (Catch "VarDeclaredNames")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:access __x5__ = (Finally "VarDeclaredNames")
  |  2:let __x6__ = __x5__
  |  2:let __x7__ = 0i
  |  2:while (< __x7__ __x6__.length) {
  |    let __x8__ = __x6__[__x7__]
  |    append __x8__ -> names
  |    __x7__ = (+ __x7__ 1i)
  |  }
  |  3:return names
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _names_ be VarDeclaredNames of |Block|.""",
    """        1. Append to _names_ the elements of the VarDeclaredNames of |Catch|.""",
    """        1. Append to _names_ the elements of the VarDeclaredNames of |Finally|.""",
    """        1. Return _names_.""",
  )
}
