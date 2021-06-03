package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArrowFunction[0,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("ArrowFunction", 0, 0, Rhs(List(NonTerminal("ArrowParameters", List(""), false), Terminal("=>"), NonTerminal("ConciseBody", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (|| (|| (|| (is-instance-of symbol NewTarget) (is-instance-of symbol SuperProperty)) (is-instance-of symbol SuperCall)) (= symbol "super")) (= symbol "this"))) return false else 1:{}
  |  1:access __x0__ = (ArrowParameters "Contains" symbol)
  |  1:if (= __x0__ true) return true else 1:{}
  |  2:access __x1__ = (ConciseBody "Contains" symbol)
  |  2:return __x1__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is not one of |NewTarget|, |SuperProperty|, |SuperCall|, `super` or `this`, return *false*.""",
    """        1. If |ArrowParameters| Contains _symbol_ is *true*, return *true*.""",
    """        1. Return |ConciseBody| Contains _symbol_.""",
  )
}
