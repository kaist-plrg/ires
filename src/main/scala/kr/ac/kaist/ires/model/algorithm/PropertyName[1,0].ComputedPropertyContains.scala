package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyName[1,0].ComputedPropertyContains` extends Algo {
  val head = SyntaxDirectedHead("PropertyName", 1, 0, Rhs(List(NonTerminal("ComputedPropertyName", List(""), false)), None), "ComputedPropertyContains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-computedpropertycontains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (ComputedPropertyName "Contains" symbol)
  |  0:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Return the result of |ComputedPropertyName| Contains _symbol_.""",
  )
}
