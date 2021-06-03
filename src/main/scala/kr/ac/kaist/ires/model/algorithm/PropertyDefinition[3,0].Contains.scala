package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::PropertyDefinition[3,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("PropertyDefinition", 3, 0, Rhs(List(NonTerminal("MethodDefinition", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:if (is-instance-of symbol MethodDefinition) return true else 1:{}
  |  1:access __x0__ = (MethodDefinition "ComputedPropertyContains" symbol)
  |  1:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is |MethodDefinition|, return *true*.""",
    """        1. Return the result of ComputedPropertyContains for |MethodDefinition| with argument _symbol_.""",
  )
}
