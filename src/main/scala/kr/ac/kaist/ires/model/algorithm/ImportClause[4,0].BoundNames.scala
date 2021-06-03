package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ImportClause[4,0].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ImportClause", 4, 0, Rhs(List(NonTerminal("ImportedDefaultBinding", List(""), false), Terminal(","), NonTerminal("NamedImports", List(""), false)), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ImportedDefaultBinding "BoundNames")
  |  0:let names = __x0__
  |  1:access __x1__ = (NamedImports "BoundNames")
  |  1:let __x2__ = __x1__
  |  1:let __x3__ = 0i
  |  1:while (< __x3__ __x2__.length) {
  |    let __x4__ = __x2__[__x3__]
  |    append __x4__ -> names
  |    __x3__ = (+ __x3__ 1i)
  |  }
  |  2:return names
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _names_ be the BoundNames of |ImportedDefaultBinding|.""",
    """        1. Append to _names_ the elements of the BoundNames of |NamedImports|.""",
    """        1. Return _names_.""",
  )
}
