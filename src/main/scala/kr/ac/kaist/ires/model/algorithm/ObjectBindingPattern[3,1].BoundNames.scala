package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ObjectBindingPattern[3,1].BoundNames` extends Algo {
  val head = SyntaxDirectedHead("ObjectBindingPattern", 3, 1, Rhs(List(Terminal("{"), NonTerminal("BindingPropertyList", List(""), false), Terminal(","), NonTerminal("BindingRestProperty", List(""), false), Terminal("}")), None), "BoundNames", List())
  val ids = List(
    "sec-static-semantics-boundnames",
    "sec-syntax-directed-operations-scope-analysis",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (BindingPropertyList "BoundNames")
  |  0:let names = __x0__
  |  1:access __x1__ = (BindingRestProperty "BoundNames")
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
    """        1. Let _names_ be BoundNames of |BindingPropertyList|.""",
    """        1. Append to _names_ the elements of BoundNames of |BindingRestProperty|.""",
    """        1. Return _names_.""",
  )
}
