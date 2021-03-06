package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::CallExpression[5,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("CallExpression", 5, 0, Rhs(List(NonTerminal("CallExpression", List(""), false), Terminal("."), NonTerminal("IdentifierName", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:access __x0__ = (CallExpression "Contains" symbol)
  |  0:if (= __x0__ true) return true else 1:{}
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If |CallExpression| Contains _symbol_ is *true*, return *true*.""",
    """        1. Return *false*.""",
  )
}
