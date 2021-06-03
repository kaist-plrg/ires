package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::SuperProperty[1,0].Contains` extends Algo {
  val head = SyntaxDirectedHead("SuperProperty", 1, 0, Rhs(List(Terminal("super"), Terminal("."), NonTerminal("IdentifierName", List(""), false)), None), "Contains", List(Param("symbol", Normal)))
  val ids = List(
    "sec-static-semantics-contains",
    "sec-syntax-directed-operations-contains",
    "sec-syntax-directed-operations",
  )
  val rawBody = parseInst("""{
  |  0:??? "If id:{symbol} is the nt:{ReservedWord} code:{super} , return value:{true} ."
  |  1:return false
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. If _symbol_ is the |ReservedWord| `super`, return *true*.""",
    """        1. Return *false*.""",
  )
}
