package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::AsyncArrowFunction[0,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("AsyncArrowFunction", 0, 0, Rhs(List(Terminal("async"), NonTerminal("AsyncArrowBindingIdentifier", List(""), false), Terminal("=>"), NonTerminal("AsyncConciseBody", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (! (|| (|| (|| (|| (is-instance-of symbol NewTarget) (is-instance-of symbol SuperProperty)) (is-instance-of symbol SuperCall)) (= symbol "super")) (= symbol "this"))) return false else 1:{}
  |  1:access __x0__ = (AsyncConciseBody "Contains" symbol)
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is not one of |NewTarget|, |SuperProperty|, |SuperCall|, `super`, or `this`, return *false*.""",
    """        1. Return |AsyncConciseBody| Contains _symbol_.""",
  )
}
